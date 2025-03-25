import org.junit.Test
import org.junit.Assert.*

class MainKtTest {

    @Test
    fun calculateTransferVkMin() {
        val cardType = "VK pay"
        val previousTransfers = 0.0
        val transferAmount = 500.0
        val result = calculateTransfer(cardType, previousTransfers, transferAmount)
        assertEquals("Сумма перевода: 500.0 руб., Комиссия: 0.0 руб., Получит: 500.0 руб. (карта VK pay)", result)
    }

    @Test
    fun calculateTransferVkMax() {
        val cardType = "VK pay"
        val previousTransfers = 0.0
        val transferAmount = 14_000.0
        val result = calculateTransfer(cardType, previousTransfers, transferAmount)
        assertEquals("Сумма перевода: 14000.0 руб., Комиссия: 0.0 руб., Получит: 14000.0 руб. (карта VK pay)", result)
    }

    @Test
    fun calculateTransferMcMin() {
        val cardType = "Mastercard"
        val previousTransfers = 0.0
        val transferAmount = 75_000.0
        val result = calculateTransfer(cardType, previousTransfers, transferAmount)
        assertEquals(
            "Сумма перевода: 75000.0 руб., Комиссия: 0.0 руб., Получит: 75000.0 руб. (карта Mastercard)",
            result
        )
    }

    @Test
    fun calculateTransferMcMax() {
        val cardType = "Mastercard"
        val previousTransfers = 0.0
        val transferAmount = 100_000.0
        val result = calculateTransfer(cardType, previousTransfers, transferAmount)
        assertEquals(
            "Сумма перевода: 100000.0 руб., Комиссия: 170.0 руб., Получит: 99830.0 руб. (карта Mastercard)",
            result
        )
    }

    @Test
    fun calculateTransferMaMin() {
        val cardType = "Maestro"
        val previousTransfers = 0.0
        val transferAmount = 75_000.0
        val result = calculateTransfer(cardType, previousTransfers, transferAmount)
        assertEquals(
            "Сумма перевода: 75000.0 руб., Комиссия: 0.0 руб., Получит: 75000.0 руб. (карта Maestro)",
            result
        )
    }

    @Test
    fun calculateTransferMaMax() {
        val cardType = "Maestro"
        val previousTransfers = 0.0
        val transferAmount = 100_000.0
        val result = calculateTransfer(cardType, previousTransfers, transferAmount)
        assertEquals(
            "Сумма перевода: 100000.0 руб., Комиссия: 170.0 руб., Получит: 99830.0 руб. (карта Maestro)",
            result
        )
    }

    @Test
    fun calculateTransferVisa() {
        val cardType = "Visa"
        val previousTransfers = 0.0
        val transferAmount = 75_000.0
        val result = calculateTransfer(cardType, previousTransfers, transferAmount)
        assertEquals(
            "Сумма перевода: 75000.0 руб., Комиссия: 562.5 руб., Получит: 74437.5 руб. (карта Visa)",
            result
        )
    }

    @Test
    fun noNameCard() {
        val cardType = "UralSib"
        val previousTransfers = 0.0
        val transferAmount = 100_000.0
        val result = calculateTransfer(cardType, previousTransfers, transferAmount)
        assertEquals(
            "Неизвестный тип карты", result
        )
    }

    @Test
    fun monthlyLimitVisa() {
        val cardType = "Visa"
        val previousTransfers = 590_000.0
        val transferAmount = 90_000.0
        val result = calculateTransfer(cardType, previousTransfers, transferAmount)
        assertEquals(
            "Перевод превышает месячный лимит в 600 000 руб.", result
        )
    }

    @Test
    fun dailyLimitVisa() {
        val cardType = "Visa"
        val previousTransfers = 0.0
        val transferAmount = 190_000.0
        val result = calculateTransfer(cardType, previousTransfers, transferAmount)
        assertEquals(
            "Перевод превышает суточный лимит в 150 000 руб.", result
        )
    }

    @Test
    fun minLimitMaestro() {
        val cardType = "Maestro"
        val previousTransfers = 0.0
        val transferAmount = 250.0
        val result = calculateTransfer(cardType, previousTransfers, transferAmount)
        assertEquals(
            "Перевод меньше 300 руб.", result
        )
    }

    @Test
    fun minLimitMastercard() {
        val cardType = "Mastercard"
        val previousTransfers = 0.0
        val transferAmount = 250.0
        val result = calculateTransfer(cardType, previousTransfers, transferAmount)
        assertEquals(
            "Перевод меньше 300 руб.", result
        )
    }

    @Test
    fun dailyLimitVk() {
        val cardType = "VK pay"
        val previousTransfers = 0.0
        val transferAmount = 19_000.0
        val result = calculateTransfer(cardType, previousTransfers, transferAmount)
        assertEquals(
            "Перевод превышает суточный лимит в 15 000 руб.", result
        )
    }

    @Test
    fun monthlyLimitVk() {
        val cardType = "VK pay"
        val previousTransfers = 39_000.0
        val transferAmount = 13_000.0
        val result = calculateTransfer(cardType, previousTransfers, transferAmount)
        assertEquals(
            "Перевод превышает месячный лимит в 40 000 руб.", result
        )
    }

    @Test
    fun commissionVisa() {
        val cardType = "Visa"
        val previousTransfers = 0.0
        val transferAmount = 1_000.0
        val result = calculateTransfer(cardType, previousTransfers, transferAmount)
        assertEquals(
            "Сумма перевода: 1000.0 руб., Комиссия: 35.0 руб., Получит: 965.0 руб. (карта Visa)", result
        )
    }

    @Test
    fun commissionMastercardMin() {
        val cardType = "Mastercard"
        val previousTransfers = 0.0
        val transferAmount = 47_000.0
        val result = calculateTransfer(cardType, previousTransfers, transferAmount)
        assertEquals(
            "Сумма перевода: 47000.0 руб., Комиссия: 0.0 руб., Получит: 47000.0 руб. (карта Mastercard)", result
        )
    }

    @Test
    fun commissionMastercardMax() {
        val cardType = "Mastercard"
        val previousTransfers = 0.0
        val transferAmount = 77_000.0
        val result = calculateTransfer(cardType, previousTransfers, transferAmount)
        assertEquals(
            "Сумма перевода: 77000.0 руб., Комиссия: 32.0 руб., Получит: 76968.0 руб. (карта Mastercard)", result
        )
    }

    @Test
    fun commissionMaestroMin() {
        val cardType = "Maestro"
        val previousTransfers = 0.0
        val transferAmount = 47_000.0
        val result = calculateTransfer(cardType, previousTransfers, transferAmount)
        assertEquals(
            "Сумма перевода: 47000.0 руб., Комиссия: 0.0 руб., Получит: 47000.0 руб. (карта Maestro)", result
        )
    }

    @Test
    fun commissionMaestroMax() {
        val cardType = "Maestro"
        val previousTransfers = 0.0
        val transferAmount = 77_000.0
        val result = calculateTransfer(cardType, previousTransfers, transferAmount)
        assertEquals(
            "Сумма перевода: 77000.0 руб., Комиссия: 32.0 руб., Получит: 76968.0 руб. (карта Maestro)", result
        )
    }

    @Test
    fun limit() {
        val transferAmount = 77_000.0
        val dailyLimit = 150_000.0
        val cardType = "Visa"
        val result = transferAmount > dailyLimit && cardType != "VK pay"
        assertEquals(false, result)
    }

}