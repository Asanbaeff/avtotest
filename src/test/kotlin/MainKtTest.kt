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
    fun dailyLimitVisaMax() {
        val cardType = "Visa"
        val previousTransfers = 0.0
        val transferAmount = 190_000.0
        val result = calculateTransfer(cardType, previousTransfers, transferAmount)
        assertEquals(
            "Перевод превышает суточный лимит в 150 000 руб.", result
        )
    }

    @Test
    fun dailyLimitVisaMin() {
        val cardType = "Visa"
        val dailyLimit = 150_000.0
        val transferAmount = 140_000.0
        val result = transferAmount > dailyLimit && cardType != "VK pay"
        assertEquals(false, result)
    }

    @Test
    fun dailyLimitVisaMinTrue() {
        val cardType = "Visa"
        val dailyLimit = 150_000.0
        val transferAmount = 160_000.0
        val result = transferAmount > dailyLimit && cardType != "VK pay"
        assertEquals(true, result)
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
    fun limitDailyVisa() {
        val transferAmount = 77_000.0
        val dailyLimit = 150_000.0
        val cardType = "Visa"
        val result = (transferAmount > dailyLimit && cardType != "VK pay")
        assertEquals(false, result)
    }

    @Test
    fun limitMonthlyVisaFalse() {
        val cardType = "Visa"
        val transferAmount = 100_000.0
        val monthlyLimit = 600_000.0
        val previousTransfers = 500_000.0
        val result = (previousTransfers + transferAmount > monthlyLimit && cardType != "VK pay")
        assertEquals(false, result)
    }

    @Test
    fun limitMonthlyVisaTrue() {
        val cardType = "Visa"
        val transferAmount = 100_000.0
        val monthlyLimit = 600_000.0
        val previousTransfers = 200_000.0
        val result = (previousTransfers + transferAmount > monthlyLimit && cardType != "VK pay")
        assertEquals(false, result)
    }

    @Test
    fun limitMonthlyVKTrue() {
        val cardType = "Мир"
        val transferAmount = 100_000.0
        val monthlyLimit = 600_000.0
        val previousTransfers = 200_000.0
        val result = (previousTransfers + transferAmount > monthlyLimit && cardType != "VK pay")
        assertEquals(false, result)
    }

    @Test
    fun limitDailyVK() {
        val cardType = "VK pay"
        val transferAmount = 14_000.0
        val dailyLimit = 15_000.0
        val result = transferAmount > dailyLimit && cardType == "VK pay"
        assertEquals(false, result)
    }

    @Test
    fun limitMonthlyVK() {
        val cardType = "VK pay"
        val transferAmount = 39_000.0
        val monthlyLimit = 40_000.0
        val previousTransfers = 0.0
        val result = previousTransfers + transferAmount > monthlyLimit && cardType == "VK pay"
        assertEquals(false, result)
    }

    @Test
    fun testTransferExceedsDailyLimit() {
        val transferAmount = 160_000.0
        val dailyLimit = 150_000.0
        val cardType = "Mastercard"
        val result = calculateTransfer(cardType, dailyLimit, transferAmount)
        assertEquals("Перевод превышает суточный лимит в 150 000 руб.", result)
    }

    @Test
    fun testTransferWithinDailyLimit() {
        val transferAmount = 140_000.0
        val dailyLimit = 150_000.0
        val cardType = "Mastercard"
        val result = calculateTransfer(cardType, dailyLimit, transferAmount)
        assertEquals(
            "Сумма перевода: 140000.0 руб., Комиссия: 860.0 руб., Получит: 139140.0 руб. (карта Mastercard)",
            result
        )
    }

    @Test
    fun testTransferExceedsDailyLimitForVKPay() {
        val transferAmount = 160_000.0
        val dailyLimit = 150_000.0
        val cardType = "VK pay"
        val result = calculateTransfer(cardType, dailyLimit, transferAmount)
        assertEquals("Перевод превышает суточный лимит в 15 000 руб.", result)
    }

    @Test
    fun testTransferEqualsDailyLimit() {
        val transferAmount = 150_000.0
        val dailyLimit = 150_000.0
        val cardType = "Mastercard"
        val result = calculateTransfer(cardType, dailyLimit, transferAmount)
        assertEquals(
            "Сумма перевода: 150000.0 руб., Комиссия: 920.0 руб., Получит: 149080.0 руб. (карта Mastercard)",
            result
        )
    }

    @Test
    fun testTransferExceedsMonthlyLimit() {
        val previousTransfers = 100_000.0
        val transferAmount = 60_000.0
        val monthlyLimit = 150_000.0
        val cardType = "Mastercard"
        val result = calculateTransfer(cardType, monthlyLimit, transferAmount)
        assertEquals(
            "Сумма перевода: 60000.0 руб., Комиссия: 380.0 руб., Получит: 59620.0 руб. (карта Mastercard)",
            result
        )
    }

    @Test
    fun testTransferWithinMonthlyLimit() {
        val previousTransfers = 80_000.0
        val transferAmount = 50_000.0
        val monthlyLimit = 150_000.0
        val cardType = "Mastercard"
        val result = calculateTransfer(cardType, monthlyLimit, transferAmount)
        assertEquals(
            "Сумма перевода: 50000.0 руб., Комиссия: 320.0 руб., Получит: 49680.0 руб. (карта Mastercard)",
            result
        )
    }

    @Test
    fun testTransferExceedsMonthlyLimitForVKPay() {
        val previousTransfers = 100_000.0
        val transferAmount = 60_000.0
        val monthlyLimit = 150_000.0
        val cardType = "VK pay"
        val result = calculateTransfer(cardType, monthlyLimit, transferAmount)
        assertEquals(
            "Перевод превышает суточный лимит в 15 000 руб.",
            result
        )
    }

    @Test
    fun testTransferEqualsMonthlyLimit() {
        val previousTransfers = 100_000.0
        val transferAmount = 50_000.0
        val monthlyLimit = 150_000.0
        val cardType = "Mastercard"
        val result = calculateTransfer(cardType, monthlyLimit, transferAmount)
        assertEquals(
            "Сумма перевода: 50000.0 руб., Комиссия: 320.0 руб., Получит: 49680.0 руб. (карта Mastercard)",
            result
        )
    }

    @Test
    fun testTransferBelowMastercardLimitForMastercard() {
        val transferAmount = 5_000.0
        val monthlyLimit = 10_000.0
        val cardType = "Mastercard"

        val result = calculateTransfer(cardType, monthlyLimit, transferAmount)
        assertEquals(
            "Сумма перевода: 5000.0 руб., Комиссия: 0.0 руб., Получит: 5000.0 руб. (карта Mastercard)", result
        )
    }

    @Test
    fun testTransferBelowMastercardLimitForMaestro() {
        val transferAmount = 5_000.0
        val monthlyLimit = 10_000.0
        val cardType = "Maestro"

        val result = calculateTransfer(cardType, monthlyLimit, transferAmount)
        assertEquals(
            "Сумма перевода: 5000.0 руб., Комиссия: 0.0 руб., Получит: 5000.0 руб. (карта Maestro)", result
        )
    }

    @Test
    fun testTransferAboveMastercardLimitForMastercard() {
        val transferAmount = 15_000.0
        val monthlyLimit = 10_000.0
        val cardType = "Mastercard"

        val result = calculateTransfer(cardType, monthlyLimit, transferAmount)
        assertEquals(
            "Сумма перевода: 15000.0 руб., Комиссия: 0.0 руб., Получит: 15000.0 руб. (карта Mastercard)", result
        )
    }

    @Test
    fun testTransferAboveMastercardLimitForMaestro() {
        val transferAmount = 15_000.0
        val monthlyLimit = 10_000.0
        val cardType = "Maestro"

        val result = calculateTransfer(cardType, monthlyLimit, transferAmount)
        assertEquals(
            "Сумма перевода: 15000.0 руб., Комиссия: 0.0 руб., Получит: 15000.0 руб. (карта Maestro)", result
        )
    }

    @Test
    fun testTransferBelowMastercardLimitForOtherCard() {
        val transferAmount = 5_000.0
        val monthlyLimit = 10_000.0
        val cardType = "Visa"

        val result = calculateTransfer(cardType, monthlyLimit, transferAmount)
        assertEquals(
            "Сумма перевода: 5000.0 руб., Комиссия: 37.5 руб., Получит: 4962.5 руб. (карта Visa)", result
        )
    }

    @Test
    fun testTransferEqualsMastercardLimitForMastercard() {
        val transferAmount = 10_000.0
        val monthlyLimit = 10_000.0
        val cardType = "Mastercard"

        val result = calculateTransfer(cardType, monthlyLimit, transferAmount)
        assertEquals(
            "Сумма перевода: 10000.0 руб., Комиссия: 0.0 руб., Получит: 10000.0 руб. (карта Mastercard)", result
        )
    }

    @Test
    fun testCalculateTransferForMastercard() {
        val transferAmount = 10_000.0
        val monthlyLimit = 150_000.0
        val cardType = "Mastercard"
        val result = calculateTransfer(cardType, monthlyLimit, transferAmount)
        assertEquals(
            "Сумма перевода: 10000.0 руб., Комиссия: 80.0 руб., Получит: 9920.0 руб. (карта Mastercard)",
            result,
        )

    }

    @Test
    fun testMastercardCommissionBelowLimit() {
        val transferAmount = 10000.0
        val cardType = "Mastercard"
        val previousTransfers = 5000.0
        val mastercardLimit = 60000.0

        val commission = calculateTransfer(cardType, mastercardLimit, transferAmount)

        assertEquals("Сумма перевода: 10000.0 руб., Комиссия: 0.0 руб., Получит: 10000.0 руб. (карта Mastercard)", commission)
    }

    @Test
    fun testMastercardCommissionAboveLimit() {
        val transferAmount = 10000.0
        val cardType = "Mastercard"
        val previousTransfers = 60000.0

        val commission = calculateTransfer(cardType, previousTransfers, transferAmount)

        assertEquals("Сумма перевода: 10000.0 руб., Комиссия: 0.0 руб., Получит: 10000.0 руб. (карта Mastercard)", commission)
    }

    @Test
    fun testVisaCommission() {
        val transferAmount = 5000.0
        val cardType = "Visa"
        val previousTransfers = 0.0

        val commission = calculateTransfer(cardType, previousTransfers, transferAmount)

        assertEquals("Сумма перевода: 5000.0 руб., Комиссия: 37.5 руб., Получит: 4962.5 руб. (карта Visa)", commission)
    }

    @Test
    fun testVisaCommissionBelowMinimum() {
        val transferAmount = 4000.0
        val cardType = "Visa"
        val previousTransfers = 0.0

        val commission = calculateTransfer(cardType, previousTransfers, transferAmount)

        assertEquals("Сумма перевода: 4000.0 руб., Комиссия: 35.0 руб., Получит: 3965.0 руб. (карта Visa)", commission)
    }

    @Test
    fun testVKPayCommission() {
        val transferAmount = 10000.0
        val cardType = "VK pay"
        val previousTransfers = 0.0

        val commission = calculateTransfer(cardType, previousTransfers, transferAmount)

        assertEquals("Сумма перевода: 10000.0 руб., Комиссия: 0.0 руб., Получит: 10000.0 руб. (карта VK pay)", commission)
    }


}