fun main() {
    //println(calculateTransfer("Мир", 0.0, 100_000.0)) // Мир
    //println(calculateTransfer("Mastercard", 0.0, 47_000.0)) // Mastercard
    //println(calculateTransfer("Visa", 0.0, 1500.0)) // Visa
}

fun calculateTransfer(
    cardType: String, //тип карты
    previousTransfers: Double, //сумма предыдущих переводов в этом месяце
    transferAmount: Double   //сумма совершаемого перевода
): String {
    val dailyLimit = 150_000.0 // Лимиты
    val monthlyLimit = 600_000.0
    val mastercardLimit = 75_000.0
    val mastercardLimitMin = 300.0
    val dailyLimitVK = 15_000.0
    val monthlyLimitVK = 40_000.0

    if (transferAmount > dailyLimit && cardType != "VK pay") { // Проверка лимитов
        return "Перевод превышает суточный лимит в 150 000 руб."
    }
    if (transferAmount < mastercardLimitMin && (cardType == "Mastercard" || cardType == "Maestro")) {
        return "Перевод меньше 300 руб."
    }
    if (previousTransfers + transferAmount > monthlyLimit && cardType != "VK pay") {
        return "Перевод превышает месячный лимит в 600 000 руб."
    }
    if (transferAmount > dailyLimitVK && cardType == "VK pay") {
        return "Перевод превышает суточный лимит в 15 000 руб."
    }
    if (previousTransfers + transferAmount > monthlyLimitVK && cardType == "VK pay") {
        return "Перевод превышает месячный лимит в 40 000 руб."
    }


    val commission: Double = when (cardType) { // Расчет комиссии
        "Mastercard", "Maestro" -> {
            if (previousTransfers >= mastercardLimit) (transferAmount * 0.006 + 20)
            else if (previousTransfers + transferAmount > mastercardLimit && previousTransfers < mastercardLimit) {
                ((previousTransfers + transferAmount - mastercardLimit) * 0.006 + 20)
            } else 0.0
        }

        "Visa", "Мир" -> {
            val calculatedCommission = transferAmount * 0.0075
            if (calculatedCommission < 35) 35.0 else calculatedCommission
        }

        "VK pay" -> 0.0

        else -> return "Неизвестный тип карты"
    }
    val totalReceived = transferAmount - commission
    return "Сумма перевода: $transferAmount руб., Комиссия: $commission руб., Получит: $totalReceived руб. (карта $cardType)"
}

