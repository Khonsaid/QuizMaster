package uz.gita.latizx.quiz.data

import uz.gita.latizx.quiz.R
import uz.gita.latizx.quiz.data.models.CategoryModel
import uz.gita.latizx.quiz.data.models.QuestionModel
import uz.gita.latizx.quiz.utils.CategoryEnum

class AppRepository private constructor() {
    private val localStorage = LocalStorage.instance
    private lateinit var listCategory: ArrayList<CategoryModel>

    companion object {
        private lateinit var instance: AppRepository

        fun getInstance(): AppRepository {
            if (!(::instance.isInitialized)) instance = AppRepository()
            return instance
        }
    }

    init {
        loadCategory()
    }

    fun getAllCategory(): List<CategoryModel> {
        loadCategory()
        return listCategory
    }

    fun setLevel(category: CategoryEnum) {
        localStorage.saveLevel(category, localStorage.getLevel(category) + 1)
    }

    fun getQuestionSizeByCategory(categoryEnum: CategoryEnum): Int = listCategory.find { it.categoryEnum == categoryEnum }!!.questionCount

    fun getLevel(categoryEnum: CategoryEnum): Int = localStorage.getLevel(categoryEnum)
    fun getQuestionByLevel(categoryEnum: CategoryEnum): List<QuestionModel> = getQuestions(categoryEnum)

    private fun getQuestions(categoryEnum: CategoryEnum): List<QuestionModel> {
        val startIndex = localStorage.getLevel(categoryEnum) * 10
        val endIndex = startIndex + 10
        return getListByCategory(categoryEnum).subList(startIndex, endIndex)
    }

    private fun loadCategory() {
        listCategory = arrayListOf(
            CategoryModel(
                CategoryEnum.GENERAL,
                R.drawable.img_general_question,
                getListByCategory(CategoryEnum.GENERAL).size,
                localStorage.getLevel(CategoryEnum.GENERAL)
            ),
            CategoryModel(
                CategoryEnum.LOGICAL,
                R.drawable.img_what_are_you_doing,
                getListByCategory(CategoryEnum.LOGICAL).size,
                localStorage.getLevel(CategoryEnum.LOGICAL)
            ),
            CategoryModel(
                CategoryEnum.Historical,
                R.drawable.img_direction,
                getListByCategory(CategoryEnum.Historical).size,
                localStorage.getLevel(CategoryEnum.Historical)
            ),
            CategoryModel(
                CategoryEnum.GEOGRAPHICAL,
                R.drawable.img_planet,
                getListByCategory(CategoryEnum.GEOGRAPHICAL).size,
                localStorage.getLevel(CategoryEnum.GEOGRAPHICAL)
            )
        )
    }

    private fun getListByCategory(categoryEnum: CategoryEnum): List<QuestionModel> =
        when (categoryEnum) {
            CategoryEnum.GENERAL -> {
                arrayListOf(
                    QuestionModel(
                        question = "Buqalar jangi eng mashhur boʻlgan davlat qaysi?",
                        variants = listOf("Braziliya", "Italiya", "Ispaniya", "Argentina"),
                        answer = "Ispaniya",
                        selectedVariant = "",
                    ),
                    QuestionModel(
                        question = "Bronza qaysi moddalarning aralashmasi hisoblanadi?",
                        variants = listOf("mis va qalay", "temir va mis", "mis va kumush", "temir va kumish"),
                        answer = "mis va qalay",
                        selectedVariant = ""
                    ),
                    QuestionModel(
                        question = "Mavsumiga koʻra yoʻnalishini oʻzgartiradigan shamollar nima deb nomlanadi?",
                        variants = listOf("passat", "shtil shamoli", "mistral", "musson"),
                        answer = "musson",
                        selectedVariant = ""
                    ),
                    QuestionModel(
                        question = "Dunyodagi eng baland sharshara qaysi?",
                        variants = listOf("Iguassu", "Anxel", "Niagara", "Viktoriya"),
                        answer = "Anxel",
                        selectedVariant = ""
                    ),
                    QuestionModel(
                        question = "Kabisa yili necha kundan iborat?",
                        variants = listOf("365", "366", "367", "354"),
                        answer = "366",
                        selectedVariant = ""
                    ),
                    QuestionModel(
                        question = "Birlamchi toʻlqinlar qanday tebranadi?",
                        variants = listOf("uzuk-uzuk", "ko'ndalang", "uzunasiga", "sekin"),
                        answer = "uzunasiga",
                        selectedVariant = ""
                    ),
                    QuestionModel(
                        question = "Oyning diametri necha kilometrni tashkil qiladi?",
                        variants = listOf("3476", "3484", "2160", "3874"),
                        answer = "3476",
                        selectedVariant = ""
                    ),
                    QuestionModel(
                        question = "Eng chuqur okean qaysi?",
                        variants = listOf("Tinch okeani", "Hind okeani", "Atlantika okeani", "Shimolit muz okeani"),
                        answer = "Tinch okeani",
                        selectedVariant = ""
                    ),
                    QuestionModel(
                        question = "Olimlar hisob-kitobiga koʻra, Yer necha yoshda?",
                        variants = listOf("1 500 000 000", "6 500 000 000", "5 500 000 000", "15 500 000 000"),
                        answer = "5 500 000 000",
                        selectedVariant = ""
                    ),
                    QuestionModel(
                        question = "Dunyodagi eng katta artezian hududi qayerda joylashgan?",
                        variants = listOf("Avstraliyada", "AQSHda", "Buyuk Britaniyada", "Xitoyda"),
                        answer = "Avstraliyada",
                        selectedVariant = ""
                    ),
                    QuestionModel(
                        question = "Yerning ekvatorial radiusi qanchaga teng?",
                        variants = listOf("6378 km", "6371 km", "6400 km", "6356 km"),
                        answer = "6378 km",
                        selectedVariant = ""
                    ),
                    QuestionModel(
                        question = "Dunyo okeanidagi eng katta suv oqimi qaysi?",
                        variants = listOf("Agulhas", "Bengal oqimi", "Kuroshio", "Golfstrim"),
                        answer = "Golfstrim",
                        selectedVariant = ""
                    ),
                    QuestionModel(
                        question = "Inson tanasidagi eng katta suyak qaysi?",
                        variants = listOf("Son suyagi", "Bilak suyagi", "Ko'krak suyagi", "Tirsak suyagi"),
                        answer = "Son suyagi",
                        selectedVariant = ""
                    ),
                    QuestionModel(
                        question = "Dunyodagi eng kichik davlat qaysi?",
                        variants = listOf("Malta", "Monako", "Vatikan", "San Marino"),
                        answer = "Vatikan",
                        selectedVariant = ""
                    ),
                    QuestionModel(
                        question = "Qaysi sayyora Quyosh tizimida eng kichikdir?",
                        variants = listOf("Venera", "Merkuriy", "Mars", "Pluton"),
                        answer = "Merkuriy",
                        selectedVariant = ""
                    ),
                    QuestionModel(
                        question = "Kislorod Yer atmosferasining necha foizini tashkil qiladi?",
                        variants = listOf("21%", "78%", "33%", "15%"),
                        answer = "21%",
                        selectedVariant = ""
                    ),
                    QuestionModel(
                        question = "Odamdagi qancha mushak harakati bilan kulish mumkin?",
                        variants = listOf("12", "26", "32", "17"),
                        answer = "17",
                        selectedVariant = ""
                    ),
                    QuestionModel(
                        question = "Dunyodagi eng katta cho'l qaysi?",
                        variants = listOf("Gobi", "Sahara", "Kalahari", "Arabiston"),
                        answer = "Sahara",
                        selectedVariant = ""
                    ),
                    QuestionModel(
                        question = "Yaponiyaning eng yirik shahri qaysi?",
                        variants = listOf("Osaka", "Tokio", "Kyoto", "Nagoya"),
                        answer = "Tokio",
                        selectedVariant = ""
                    ),
                    QuestionModel(
                        question = "Qaysi o'simlik eng uzun umr ko'radi?",
                        variants = listOf("Sekvoyya", "Evkalipt", "Kaktus", "Bargizub"),
                        answer = "Sekvoyya",
                        selectedVariant = ""
                    ),
                    QuestionModel(
                        question = "Inson tanasida nechta suyak bor?",
                        variants = listOf("216", "196", "206", "226"),
                        answer = "206",
                        selectedVariant = "",
                    ),
                    QuestionModel(
                        question = "Qaysi element davriy jadvalda 'Fe' deb belgilangan?",
                        variants = listOf("Temir", "Ftor", "Fosfor", "Ferum"),
                        answer = "Temir",
                        selectedVariant = "",
                    ),
                    QuestionModel(
                        question = "Amazon kompaniyasi qaysi yilda tashkil topgan?",
                        variants = listOf("1994", "1990", "1998", "1992"),
                        answer = "1994",
                        selectedVariant = "",
                    ),
                    QuestionModel(
                        question = "Dunyodagi eng katta arxipelag qaysi?",
                        variants = listOf("Indoneziya", "Filippin", "Yaponiya", "Maldiv"),
                        answer = "Indoneziya",
                        selectedVariant = "",
                    ),
                    QuestionModel(
                        question = "iPhone 1 qaysi yilda chiqarilgan?",
                        variants = listOf("2007", "2005", "2009", "2003"),
                        answer = "2007",
                        selectedVariant = "",
                    ),
                    QuestionModel(
                        question = "Jahon banki qaysi shaharda joylashgan?",
                        variants = listOf("Vashington", "Nyu-York", "London", "Jeneva"),
                        answer = "Vashington",
                        selectedVariant = "",
                    ),
                    QuestionModel(
                        question = "Qaysi til dunyoda eng ko'p ishlatiladigan til hisoblanadi?",
                        variants = listOf("Ingliz", "Xitoy", "Ispan", "Hindi"),
                        answer = "Ingliz",
                        selectedVariant = "",
                    ),
                    QuestionModel(
                        question = "Dunyodagi eng katta orol qaysi?",
                        variants = listOf("Grenlandiya", "Yangi Gvineya", "Borneo", "Madagaskar"),
                        answer = "Grenlandiya",
                        selectedVariant = "",
                    ),
                    QuestionModel(
                        question = "Bitcoin qaysi yilda yaratilgan?",
                        variants = listOf("2009", "2007", "2011", "2005"),
                        answer = "2009",
                        selectedVariant = "",
                    ),
                    QuestionModel(
                        question = "O'zbekistonning nechta viloyati bor?",
                        variants = listOf("12", "11", "13", "14"),
                        answer = "12",
                        selectedVariant = "",
                    ),

                    QuestionModel(
                        question = "Instagram qaysi yilda tashkil topgan?",
                        variants = listOf("2010", "2008", "2012", "2009"),
                        answer = "2010",
                        selectedVariant = "",
                    ),
                    QuestionModel(
                        question = "Dunyodagi eng qimmat brend qaysi?",
                        variants = listOf("Apple", "Microsoft", "Amazon", "Google"),
                        answer = "Apple",
                        selectedVariant = "",
                    ),
                    QuestionModel(
                        question = "Dunyo bo'yicha eng ko'p sotilgan telefon modeli qaysi?",
                        variants = listOf("iPhone 6", "Nokia 1100", "Samsung Galaxy S4", "iPhone X"),
                        answer = "Nokia 1100",
                        selectedVariant = "",
                    ),
                    QuestionModel(
                        question = "Inson miyasining og'irligi o'rtacha necha kg?",
                        variants = listOf("1.3", "2.1", "1.8", "0.9"),
                        answer = "1.3",
                        selectedVariant = "",
                    ),
                    QuestionModel(
                        question = "Netflix qaysi yilda tashkil topgan?",
                        variants = listOf("1997", "2001", "1999", "2003"),
                        answer = "1997",
                        selectedVariant = "",
                    ),
                    QuestionModel(
                        question = "Microsoft Windows qaysi yilda chiqqan?",
                        variants = listOf("1985", "1980", "1990", "1983"),
                        answer = "1985",
                        selectedVariant = "",
                    ),
                    QuestionModel(
                        question = "Eng qadimgi sivilizatsiya qayerda paydo bo'lgan?",
                        variants = listOf("Mesopotamiya", "Misr", "Xitoy", "Hindiston"),
                        answer = "Mesopotamiya",
                        selectedVariant = "",
                    ),
                    QuestionModel(
                        question = "Android operatsion tizimi qaysi yilda chiqqan?",
                        variants = listOf("2008", "2006", "2010", "2005"),
                        answer = "2008",
                        selectedVariant = "",
                    ),
                    QuestionModel(
                        question = "Dunyodagi eng chuqur okean nuqtasi qaysi?",
                        variants = listOf("Mariana o'rami", "Tonga o'rami", "Puerto-Riko o'rami", "Filippin o'rami"),
                        answer = "Mariana o'rami",
                        selectedVariant = "",
                    ),
                    QuestionModel(
                        question = "McDonald's qaysi yilda tashkil topgan?",
                        variants = listOf("1940", "1950", "1935", "1945"),
                        answer = "1940",
                        selectedVariant = "",
                    ),
                    QuestionModel(
                        question = "Dunyodagi eng uzun temir yo'l qaysi?",
                        variants = listOf("Trans-Sibir", "Pekin-Guangzhou", "Kanada Temir yo'li", "Indian Pacific"),
                        answer = "Trans-Sibir",
                        selectedVariant = "",
                    ),
                    QuestionModel(
                        question = "Dunyo bo'yicha eng ko'p ishlab chiqariladigan avtomobil markasi qaysi?",
                        variants = listOf("Toyota", "Volkswagen", "Ford", "Honda"),
                        answer = "Toyota",
                        selectedVariant = "",
                    ),
                    QuestionModel(
                        question = "Wikipedia qaysi yilda ishga tushirilgan?",
                        variants = listOf("2001", "1999", "2003", "1997"),
                        answer = "2001",
                        selectedVariant = "",
                    ),
                    QuestionModel(
                        question = "PayPal qaysi yilda tashkil topgan?",
                        variants = listOf("1998", "2000", "1996", "2002"),
                        answer = "1998",
                        selectedVariant = "",
                    ),
                    QuestionModel(
                        question = "Dunyodagi eng katta suv ombori qayerda joylashgan?",
                        variants = listOf("Kariba", "Bratsk", "Aswan", "Three Gorges"),
                        answer = "Three Gorges",
                        selectedVariant = "",
                    ),
                    QuestionModel(
                        question = "Qaysi mamlakat dunyoda eng ko'p oltin zaxirasiga ega?",
                        variants = listOf("AQSH", "Germaniya", "Italiya", "Fransiya"),
                        answer = "AQSH",
                        selectedVariant = "",
                    ),
                    QuestionModel(
                        question = "Jahon Savdo Tashkiloti qachon tashkil topgan?",
                        variants = listOf("1995", "1990", "2000", "1985"),
                        answer = "1995",
                        selectedVariant = "",
                    ),
                    QuestionModel(
                        question = "Dunyodagi eng ko'p aholisi bo'lgan shahar qaysi?",
                        variants = listOf("Tokio", "Dehli", "Shanghai", "San Paulo"),
                        answer = "Tokio",
                        selectedVariant = "",
                    ),
                    QuestionModel(
                        question = "Telegram messenjeri qaysi yilda ishga tushirilgan?",
                        variants = listOf("2013", "2011", "2015", "2010"),
                        answer = "2013",
                        selectedVariant = "",
                    ),
                    QuestionModel(
                        question = "SpaceX kompaniyasi qaysi yilda tashkil topgan?",
                        variants = listOf("2002", "2000", "2004", "1998"),
                        answer = "2002",
                        selectedVariant = "",
                    ),
                    QuestionModel(
                        question = "Qaysi tilning eng ko'p so'z boyligi bor?",
                        variants = listOf("Ingliz", "Arab", "Rus", "Xitoy"),
                        answer = "Ingliz",
                        selectedVariant = "",
                    ),
                    QuestionModel(
                        question = "Inson tanasidagi eng katta organ qaysi?",
                        variants = listOf("Teri", "Jigar", "O'pka", "Yurak"),
                        answer = "Teri",
                        selectedVariant = "",
                    ),
                    QuestionModel(
                        question = "Qaysi hayvon eng tez yuguradi?",
                        variants = listOf("Burgut", "Gepard", "Antilopа", "Arslon"),
                        answer = "Gepard",
                        selectedVariant = "",
                    ),
                    QuestionModel(
                        question = "Dunyo bo'yicha eng ko'p eksport qiladigan davlat qaysi?",
                        variants = listOf("Germaniya", "AQSH", "Xitoy", "Yaponiya"),
                        answer = "Xitoy",
                        selectedVariant = "",
                    ),
                    QuestionModel(
                        question = "Adobe kompaniyasi qaysi yilda tashkil topgan?",
                        variants = listOf("1982", "1985", "1980", "1978"),
                        answer = "1982",
                        selectedVariant = "",
                    ),
                    QuestionModel(
                        question = "Dunyodagi eng qadimgi universitet qaysi?",
                        variants = listOf("Bologna", "Al-Karauin", "Oxford", "Kembridj"),
                        answer = "Al-Karauin",
                        selectedVariant = "",
                    ),
                    QuestionModel(
                        question = "Dunyodagi eng katta kutubxona qaysi?",
                        variants = listOf(
                            "Kongress kutubxonasi",
                            "Britaniya kutubxonasi",
                            "Rossiya Milliy kutubxonasi",
                            "Xitoy Milliy kutubxonasi"
                        ),
                        answer = "Kongress kutubxonasi",
                        selectedVariant = "",
                    ),
                    QuestionModel(
                        question = "Inson DNKsining uzunligi taxminan necha metr?",
                        variants = listOf("2", "1", "3", "4"),
                        answer = "2",
                        selectedVariant = "",
                    ),
                    QuestionModel(
                        question = "Birinchi kompyuter virusi qachon yaratilgan?",
                        variants = listOf("1971", "1975", "1969", "1973"),
                        answer = "1971",
                        selectedVariant = "",
                    ),
                    QuestionModel(
                        question = "Qaysi davlat birinchi bo'lib atomni parchalagan?",
                        variants = listOf("Buyuk Britaniya", "AQSH", "Germaniya", "Sovet Ittifoqi"),
                        answer = "Buyuk Britaniya",
                        selectedVariant = "",
                    )
                )
            }

            CategoryEnum.LOGICAL -> {
                arrayListOf(
                    QuestionModel(
                        question = "Mahmudning 10 ta qo’yi bor edi. 9 tadan boshqa hamma qo’ylari o’lib qoldi. Mahmudning nechta qo’yi qoldi?",
                        variants = listOf("1", "9", "4", "10"),
                        answer = "9",
                        selectedVariant = ""
                    ),
                    QuestionModel(
                        question = "Bu shaxmat donasi sabab o’rta asrlarda arab shohlari bu o’yinni o’ynashni istashmagan.",
                        variants = listOf("Ruh", "Shox", "Ot", "Farzin"),
                        answer = "Ot",
                        selectedVariant = ""
                    ),

                    QuestionModel(
                        question = "Bu xarf deyarli dunyoning barcha alfavitlarida bir xil yozilishi bilan rekord o’rnatgan. Bu qaysi xarf?",
                        variants = listOf("A harfi", "X harfi", "O harfi", "K harfi"),
                        answer = "O harfi",
                        selectedVariant = ""
                    ),

                    QuestionModel(
                        question = "Nok osilib turibdi, lekin uni yeb bo’lmaydi. Lampochka emas.",
                        variants = listOf("Hali pishmagan", "Tog' tepasida", "Zaharli nok", "Begonaning noki"),
                        answer = "Begonaning noki",
                        selectedVariant = ""
                    ),

                    QuestionModel(
                        question = "Stolning 4 ta burchagi bor. Uning bir burchagini kesib yuborsak nechta burchak qoladi?",
                        variants = listOf("4 ta", "6 ta", "3 ta", "5 ta"),
                        answer = "5 ta",
                        selectedVariant = ""
                    ),

                    QuestionModel(
                        question = "Professor kech soat sakkizda budelnikni 9 ga qo’yib uyquga yotdi. U necha soat uxlaydi?",
                        variants = listOf("2 soat", "12 soat", "8 soat", "1 soat"),
                        answer = "1 soat",
                        selectedVariant = ""
                    ),

                    QuestionModel(
                        question = "Shunday insonlar borki, ular otni ham, filni ham qiyinchiliksiz ko’tara oladi, joyini o’zgartira oladi. Ular kimlar?",
                        variants = listOf("Traktorchilar", "Hayvon o'rgatuvchilar", "Shaxmatchilar", "Sportchilar"),
                        answer = "Shaxmatchilar",
                        selectedVariant = ""
                    ),

                    QuestionModel(
                        question = "7 ta sham yonib turgan edi. Ulardan 3 tasini o’chirib qo’yishdi. Nechta sham qoldi?",
                        variants = listOf("3 ta", "1 ta", "4 ta", "7 ta"),
                        answer = "3 ta",
                        selectedVariant = ""
                    ),

                    QuestionModel(
                        question = "Bu narsa insonga 2 marta beriladi. Uchinchisida sotib olinadi.",
                        variants = listOf("Kiyim", "Soch", "Tish", "Ilim"),
                        answer = "Tish",
                        selectedVariant = ""
                    ),
                    QuestionModel(
                        question = "Bir xonada 3 ta sham yonib turibdi. Ulardan ikkitasi o'chsa, necha sham qoladi?",
                        variants = listOf("1 ta", "2 ta", "3 ta", "0 ta"),
                        answer = "3 ta",
                        selectedVariant = ""
                    ),

                    QuestionModel(
                        question = "Qaysi hayvonning tuyog’i dumaloq shaklda bo’ladi?",
                        variants = listOf("Ot", "Eshak", "Fil", "Maymun"),
                        answer = "Ot",
                        selectedVariant = ""
                    ),

                    QuestionModel(
                        question = "Qaysi meva ichida kichik urug’lar bo’ladi?",
                        variants = listOf("Nok", "Banan", "Apelsin", "Anor"),
                        answer = "Anor",
                        selectedVariant = ""
                    ),
                    QuestionModel(
                        question = "Shaharga kirishda barcha mashinalar rangsiz bo’ladi. Bu qanday tushuntiriladi?",
                        variants = listOf("Qonuniy", "Mahsulot sifati", "Barcha mashinalar oppoq", "Rasmda ko’riladi"),
                        answer = "Rasmda ko’riladi",
                        selectedVariant = ""
                    ),

                    QuestionModel(
                        question = "5 ta qushning 10 ta oyog'i bor. 2 ta qushning nechta oyog'i bor?",
                        variants = listOf("2", "4", "6", "8"),
                        answer = "4",
                        selectedVariant = ""
                    ),
                    QuestionModel(
                        question = "Daraxtda 6 ta olma bor edi. 2 ta olma yerga tushib ketdi. Daraxtda nechta olma qoldi?",
                        variants = listOf("8", "6", "4", "2"),
                        answer = "4",
                        selectedVariant = ""
                    ),
                    QuestionModel(
                        question = "Agar bugun dushanba bo'lsa, 100 kundan keyin haftaning qaysi kuni bo'ladi?",
                        variants = listOf("Seshanba", "Chorshanba", "Payshanba", "Juma"),
                        answer = "Chorshanba",
                        selectedVariant = ""
                    ),
                    QuestionModel(
                        question = "Stolda 3 ta olma turibdi. Ulardan 2 tasini olsangiz sizda nechta olma bo'ladi?",
                        variants = listOf("1", "2", "3", "5"),
                        answer = "2",
                        selectedVariant = ""
                    ),
                    QuestionModel(
                        question = "12 sonini qaysi songa bo'lganda 4 chiqadi?",
                        variants = listOf("2", "3", "4", "6"),
                        answer = "3",
                        selectedVariant = ""
                    ),
                    QuestionModel(
                        question = "Qaysi sport turi uchun eng ko’p o’yinchilar talab qilinadi?",
                        variants = listOf("Futbol", "Basketbol", "Kriket", "Voleybol"),
                        answer = "Futbol",
                        selectedVariant = ""
                    ),
                    QuestionModel(
                        question = "Agar bir soat 60 daqiqa bo'lsa, yarim soat necha daqiqa?",
                        variants = listOf("15", "20", "30", "40"),
                        answer = "30",
                        selectedVariant = ""
                    ),
                    QuestionModel(
                        question = "To'rtta bola birgalikda 12 ta konfet yedi. Har bir bola nechta konfet yegan?",
                        variants = listOf("2", "3", "4", "6"),
                        answer = "3",
                        selectedVariant = ""
                    ),
                    QuestionModel(
                        question = "15 sonidan qaysi sonni ayirsak 8 hosil bo'ladi?",
                        variants = listOf("5", "6", "7", "9"),
                        answer = "7",
                        selectedVariant = ""
                    ),
                    QuestionModel(
                        question = "Agar kecha shanba bo'lgan bo'lsa, ertaga qanday kun bo'ladi?",
                        variants = listOf("Shanba", "Yakshanba", "Dushanba", "Seshanba"),
                        answer = "Dushanba",
                        selectedVariant = ""
                    ),
                    //
                    QuestionModel(
                        question = "Bir qutida 20 ta qalam bor. 4 ta o'quvchiga teng taqsimlansa, har biriga nechtadan qalam tegadi?",
                        variants = listOf("4", "5", "6", "8"),
                        answer = "5",
                        selectedVariant = ""
                    ),
                    QuestionModel(
                        question = "Daraxtda 8 ta qush o'tiribdi. Ovchi otganda 3 ta qush uchib ketdi. Daraxtda nechta qush qoldi?",
                        variants = listOf("3", "4", "5", "0"),
                        answer = "0",
                        selectedVariant = ""
                    ),
                    QuestionModel(
                        question = "Avtobusda 15 ta yo'lovchi bor edi. Birinchi bekatda 3 ta tushdi, ikkinchi bekatda 4 ta chiqdi. Avtobusda nechta yo'lovchi qoldi?",
                        variants = listOf("7", "8", "9", "10"),
                        answer = "8",
                        selectedVariant = ""
                    ),
                    QuestionModel(
                        question = "Bir soat oldin soat 11:00 edi. 2 soatdan keyin soat necha bo'ladi?",
                        variants = listOf("12:00", "13:00", "14:00", "15:00"),
                        answer = "14:00",
                        selectedVariant = ""
                    ),
                    QuestionModel(
                        question = "Gulzorda 12 ta atirgul o'sib turibdi. Shamol 3 tasini sindirdi, 2 tasi so'lib qoldi. Nechta atirgul qoldi?",
                        variants = listOf("5", "6", "7", "8"),
                        answer = "7",
                        selectedVariant = ""
                    ),
                    QuestionModel(
                        question = "Qaysi narsa sindirish oson, lekin tuzatish qiyin?",
                        variants = listOf("Ishonch", "Shisha", "Qog'oz", "Yurak"),
                        answer = "Ishonch",
                        selectedVariant = ""
                    ),
                    QuestionModel(
                        question = "3 ta aka-uka har biriga 2 tadan kitob sotib oldi. Jami nechta kitob sotib olishdi?",
                        variants = listOf("5", "6", "7", "8"),
                        answer = "6",
                        selectedVariant = ""
                    ),
                    QuestionModel(
                        question = "Bir hafta 7 kun. 21 kunda necha hafta bor?",
                        variants = listOf("2", "3", "4", "5"),
                        answer = "3",
                        selectedVariant = ""
                    ),
                    QuestionModel(
                        question = "To'rtburchakning 2 ta tomoni 5 sm, 2 ta tomoni 3 sm. Uning perimetri necha sm?",
                        variants = listOf("12", "14", "16", "18"),
                        answer = "16",
                        selectedVariant = ""
                    ),
                    QuestionModel(
                        question = "Bir kilogramm 1000 gramm. 2500 gramm necha kilogramm bo'ladi?",
                        variants = listOf("2", "2.5", "3", "3.5"),
                        answer = "2.5",
                        selectedVariant = ""
                    ),
                    QuestionModel(
                        question = "Bog'da 4 ta olma daraxti, 3 ta nok daraxti va 5 ta o'rik daraxti bor. Jami nechta mevali daraxt bor?",
                        variants = listOf("10", "11", "12", "13"),
                        answer = "12",
                        selectedVariant = ""
                    ),
                    //
                    QuestionModel(
                        question = "Bir odam kichik bir xonada yolg'iz o'zi. Biroq uning xavfli his qilishi mumkin. Bu nima?",
                        variants = listOf("Kaltakesak", "Oyna", "Ko'zgu", "Chiroq"),
                        answer = "Ko'zgu",
                        selectedVariant = ""
                    ),
                    QuestionModel(
                        question = "Bir sinfdagi 20 o'quvchidan 8 tasi qiz bola. O'g'il bolalar soni nechta?",
                        variants = listOf("10", "11", "12", "13"),
                        answer = "12",
                        selectedVariant = ""
                    ),
                    QuestionModel(
                        question = "Bir savatchada 15 ta tuxum bor edi. 3 tasi sinib qoldi, 4 tasidan omlet qilindi. Nechta tuxum qoldi?",
                        variants = listOf("6", "7", "8", "9"),
                        answer = "8",
                        selectedVariant = ""
                    ),
                    QuestionModel(
                        question = "Bir litr sut 1000 ml ga teng. 2500 ml sut necha litr bo'ladi?",
                        variants = listOf("2", "2.5", "3", "3.5"),
                        answer = "2.5",
                        selectedVariant = ""
                    ),
                    QuestionModel(
                        question = "Bir yashikda 24 ta olma bor. Ularni 6 ta bolaga teng bo'lib berish kerak. Har bir bolaga nechtadan olma tegadi?",
                        variants = listOf("3", "4", "5", "6"),
                        answer = "4",
                        selectedVariant = ""
                    ),
                    QuestionModel(
                        question = "Soat 15:00 da mashg'ulot boshlandi. Mashg'ulot 2 soatu 30 daqiqa davom etdi. Mashg'ulot soat nechada tugadi?",
                        variants = listOf("17:00", "17:30", "18:00", "18:30"),
                        answer = "17:30",
                        selectedVariant = ""
                    ),
                    QuestionModel(
                        question = "Akmal har kuni 2 soat dars qiladi. U bir haftada necha soat dars qiladi?",
                        variants = listOf("10", "12", "14", "16"),
                        answer = "14",
                        selectedVariant = ""
                    ),
                    QuestionModel(
                        question = "Bir futbol jamoasida 11 ta o'yinchi bor. 3 ta o'yinchi jarohat oldi. Nechta o'yinchi qoldi?",
                        variants = listOf("6", "7", "8", "9"),
                        answer = "8",
                        selectedVariant = ""
                    ),
                    QuestionModel(
                        question = "U sizga tegishli, ammo boshqalar undan sizga nisbatan ancha ko’proq foydalanishadi. U nima?",
                        variants = listOf("Pulingiz", "Mashinangiz", "Sizning ismingiz", "Sizning so'zingiz"),
                        answer = "Sizning ismingiz",
                        selectedVariant = ""
                    ),
                    QuestionModel(
                        question = "100 so'mlik 5 ta tanga va 200 so'mlik 3 ta tanga bor. Jami necha so'm pul bor?",
                        variants = listOf("1000", "1100", "1200", "1300"),
                        answer = "1100",
                        selectedVariant = ""
                    ),
                    QuestionModel(
                        question = "8 ta stulning har birida 4 tadan oyoq bor. Jami nechta stul oyog'i bor?",
                        variants = listOf("24", "28", "30", "32"),
                        answer = "32",
                        selectedVariant = ""
                    ),
                    QuestionModel(
                        question = "30 ta o'quvchidan 12 tasi sport to'garagiga, 8 tasi musiqa to'garagiga qatnashadi. Nechta o'quvchi hech qanday to'garakka qatnashmaydi?",
                        variants = listOf("8", "10", "12", "14"),
                        answer = "10",
                        selectedVariant = ""
                    ),
                    QuestionModel(
                        question = "Bir qutida 40 ta qalam bor. O'qituvchi ularni 5 ta guruhga teng taqsimladi. Har bir guruhga nechtadan qalam tegdi?",
                        variants = listOf("6", "7", "8", "10"),
                        answer = "8",
                        selectedVariant = ""
                    ),

                    QuestionModel(
                        question = "Kim o’tirgan holda yuradi?",
                        variants = listOf("Haydovchi", "Shaxmatchi", "Oshpaz", "Quruvchi"),
                        answer = "Shaxmatchi",
                        selectedVariant = ""
                    ),

                    QuestionModel(
                        question = "Bir hafta davomida har kuni 3 soatdan kitob o'qidim. Men bir haftada necha soat kitob o'qidim?",
                        variants = listOf("18", "21", "24", "28"),
                        answer = "21",
                        selectedVariant = ""
                    ),
                    QuestionModel(
                        question = "Bir bog'da 15 ta olma va 12 ta nok daraxti bor. Olma daraxtlari sonidan nok daraxtlari soni nechta kam?",
                        variants = listOf("2", "3", "4", "5"),
                        answer = "3",
                        selectedVariant = ""
                    ),
                    QuestionModel(
                        question = "Muzeydagi 25 ta rasmdan 8 tasi portret, qolgani manzara. Manzara rasmlar soni nechta?",
                        variants = listOf("15", "16", "17", "18"),
                        answer = "17",
                        selectedVariant = ""
                    ),
                    QuestionModel(
                        question = "Qaysi oyda 28 kun mavjud?",
                        variants = listOf("Fevral", "Mart", "Barcha oylarda", "Aprel"),
                        answer = "Barcha oylarda",
                        selectedVariant = ""
                    ),
                    QuestionModel(
                        question = "Avtobusga birinchi bekatda 12 kishi chiqdi, ikkinchi bekatda 5 kishi tushib, 8 kishi chiqdi. Avtobusda necha kishi bor?",
                        variants = listOf("12", "15", "18", "20"),
                        answer = "15",
                        selectedVariant = ""
                    ),
                    QuestionModel(
                        question = "Bir kilogramm go'sht 50000 so'm turadi. 1.5 kilogramm go'sht necha so'm turadi?",
                        variants = listOf("65000", "70000", "75000", "80000"),
                        answer = "75000",
                        selectedVariant = ""
                    ),
                    QuestionModel(
                        question = "Bir sinfdagi 30 o'quvchidan 20 tasi ingliz tilini, 15 tasi rus tilini o'rganadi. Ikkala tilni ham o'rganadigan o'quvchilar soni 10 ta. Hech qanday til o'rganmaydigan o'quvchilar soni nechta?",
                        variants = listOf("3", "4", "5", "6"),
                        answer = "5",
                        selectedVariant = ""
                    ),
                    QuestionModel(
                        question = "Bitta velosipedning 2 ta g'ildiragi bor. 6 ta velosipedning nechta g'ildiragi bor?",
                        variants = listOf("8", "10", "12", "14"),
                        answer = "12",
                        selectedVariant = ""
                    ),
                    QuestionModel(
                        question = "Yerdan osonlikcha ko’tarish mumkin, lekin uni uzoqqa otib bo’lmaydi. Bu nima?",
                        variants = listOf("Qush pati", "Qanot", "Barg", "Qum"),
                        answer = "Qush pati",
                        selectedVariant = ""
                    ),
                    QuestionModel(
                        question = "150 metrli arqonni har 3 metrdan qirqish kerak. Necha marta qirqish kerak bo'ladi?",
                        variants = listOf("48", "49", "50", "51"),
                        answer = "49",
                        selectedVariant = ""
                    ),
                    QuestionModel(
                        question = "Hovuzga har daqiqada 5 litr suv quyilmoqda. 10 daqiqada hovuzga necha litr suv quyiladi?",
                        variants = listOf("40", "45", "50", "55"),
                        answer = "50",
                        selectedVariant = ""
                    ),
                    QuestionModel(
                        question = "Mahmudning 10 ta qo’yi bor edi. 9 tadan boshqa hamma qo’ylari o’lib qoldi. Mahmudning nechta qo’yi qoldi?",
                        variants = listOf("1", "9", "4", "10"),
                        answer = "9",
                        selectedVariant = ""
                    ),
                )
            }

            CategoryEnum.Historical -> {
                arrayListOf(
                    QuestionModel(
                        question = "Amir Temur qaysi yilda tavallud topgan?",
                        variants = listOf("1337", "1336", "1340", "1342"),
                        answer = "1336",
                        selectedVariant = ""
                    ),
                    QuestionModel(
                        question = "O'zbekiston Respublikasi qaysi sanada mustaqillikka erishgan?",
                        variants = listOf("1989-yil 1-sentabr", "1990-yil 1-sentabr", "1991-yil 1-sentabr", "1992-yil 1-sentabr"),
                        answer = "1991-yil 1-sentabr",
                        selectedVariant = ""
                    ),
                    QuestionModel(
                        question = "Buyuk ipak yo'li qaysi mamlakatlarni bog'lagan?",
                        variants = listOf(
                            "Misr va Rim",
                            "Hindiston va Afrika",
                            "Xitoy va Yevropa",
                            "Arabiston va Hindiston"
                        ),
                        answer = "Xitoy va Yevropa",
                        selectedVariant = ""
                    ),
                    QuestionModel(
                        question = "Qadimgi Xorazm poytaxti qaysi shahar bo'lgan?",
                        variants = listOf("Urganch", "Xiva", "Tuproqqal'a", "Nukus"),
                        answer = "Tuproqqal'a",
                        selectedVariant = ""
                    ),
                    QuestionModel(
                        question = "Mirzo Ulug'bek rasadxonasi qaysi shaharda qurilgan?",
                        variants = listOf("Buxoro", "Samarqand", "Toshkent", "Shahrisabz"),
                        answer = "Samarqand",
                        selectedVariant = ""
                    ),
                    QuestionModel(
                        question = "Qaysi yilda Toshkent metro qurilishi boshlangan?",
                        variants = listOf("1972", "1973", "1974", "1975"),
                        answer = "1973",
                        selectedVariant = ""
                    ),
                    QuestionModel(
                        question = "Al-Xorazmiy qaysi sohalarda ijod qilgan?",
                        variants = listOf(
                            "Matematika va astronomiya",
                            "Tibbiyot va kimyo",
                            "Adabiyot va san'at",
                            "Arxitektura va musiqa"
                        ),
                        answer = "Matematika va astronomiya",
                        selectedVariant = ""
                    ),
                    QuestionModel(
                        question = "Bobur Mirzo qachon Hindistonga yurish qilgan?",
                        variants = listOf("1524", "1525", "1526", "1527"),
                        answer = "1526",
                        selectedVariant = ""
                    ),
                    QuestionModel(
                        question = "O'zbekiston Respublikasining birinchi Konstitutsiyasi qachon qabul qilingan?",
                        variants = listOf(
                            "1991-yil 8-dekabr",
                            "1992-yil 8-dekabr",
                            "1993-yil 8-dekabr",
                            "1994-yil 8-dekabr"
                        ),
                        answer = "1992-yil 8-dekabr",
                        selectedVariant = ""
                    ),
                    QuestionModel(
                        question = "Qaysi yilda Toshkent zilzilasi bo'lib o'tgan?",
                        variants = listOf("1966", "1967", "1968", "1969"),
                        answer = "1966",
                        selectedVariant = ""
                    ),
                    QuestionModel(
                        question = "Ibn Sino qaysi asarning muallifi?",
                        variants = listOf(
                            "Tib qonunlari",
                            "Xamsa",
                            "Boburnoma",
                            "Ziji Ko'ragoniy"
                        ),
                        answer = "Tib qonunlari",
                        selectedVariant = ""
                    ),
                    QuestionModel(
                        question = "Beruniy necha yil umr ko'rgan?",
                        variants = listOf("70", "75", "80", "85"),
                        answer = "80",
                        selectedVariant = ""
                    ),
                    QuestionModel(
                        question = "Qaysi yilda Xiva xonligi Rossiya tomonidan bosib olingan?",
                        variants = listOf("1873", "1874", "1875", "1876"),
                        answer = "1873",
                        selectedVariant = ""
                    ),
                    QuestionModel(
                        question = "Quyidagi olimlardan qaysi biri 'Algoritm' so'zining kelib chiqishiga asos bo'lgan?",
                        variants = listOf("Al-Xorazmiy", "Al-Farg'oniy", "Al-Beruniy", "Ibn Sino"),
                        answer = "Al-Xorazmiy",
                        selectedVariant = ""
                    ),
                    QuestionModel(
                        question = "Ikkinchi jahon urushi qaysi yilda boshlangan?",
                        variants = listOf("1937", "1938", "1939", "1940"),
                        answer = "1939",
                        selectedVariant = ""
                    ),
                    QuestionModel(
                        question = "Qadimgi Rim imperiyasi qaysi yilda qulab tushgan?",
                        variants = listOf("456", "476", "486", "496"),
                        answer = "476",
                        selectedVariant = ""
                    ),
                    QuestionModel(
                        question = "Alisher Navoiy qaysi yillarda yashab o'tgan?",
                        variants = listOf(
                            "1441-1501",
                            "1451-1501",
                            "1441-1511",
                            "1441-1521"
                        ),
                        answer = "1441-1501",
                        selectedVariant = ""
                    ),
                    QuestionModel(
                        question = "Amerika qit'asi qaysi yilda Kolumb tomonidan kashf etilgan?",
                        variants = listOf("1490", "1491", "1492", "1493"),
                        answer = "1492",
                        selectedVariant = ""
                    ),
                    QuestionModel(
                        question = "Quyidagi shaharlardan qaysi biri eng qadimiy?",
                        variants = listOf("Afrosiyob", "Buxoro", "Xiva", "Qo'qon"),
                        answer = "Afrosiyob",
                        selectedVariant = ""
                    ),
                    QuestionModel(
                        question = "Buyuk geografik kashfiyotlar davri qaysi asrda boshlangan?",
                        variants = listOf("XIII asr", "XIV asr", "XV asr", "XVI asr"),
                        answer = "XV asr",
                        selectedVariant = ""
                    ),
                    QuestionModel(
                        question = "O'zbekiston BMTga a'zo bo'lib qachon qabul qilingan?",
                        variants = listOf("1991", "1992", "1993", "1994"),
                        answer = "1992",
                        selectedVariant = ""
                    ),
                    QuestionModel(
                        question = "Imom Buxoriy qaysi asarning muallifi?",
                        variants = listOf(
                            "Al-Jome as-Sahih",
                            "Xamsa",
                            "Devoni lug'atit turk",
                            "Qutadg'u bilig"
                        ),
                        answer = "Al-Jome as-Sahih",
                        selectedVariant = ""
                    ),
                    QuestionModel(
                        question = "Birinchi temir yo'l qachon O'zbekistonga keltirilgan?",
                        variants = listOf("1874", "1875", "1876", "1877"),
                        answer = "1874",
                        selectedVariant = ""
                    ),
                    QuestionModel(
                        question = "Napoleon Bonapart qaysi yilda taxtdan ag'darilgan?",
                        variants = listOf("1812", "1813", "1814", "1815"),
                        answer = "1815",
                        selectedVariant = ""
                    ),
                    QuestionModel(
                        question = "Birinchi jahon urushi qaysi yilda boshlangan?",
                        variants = listOf("1912", "1914", "1916", "1918"),
                        answer = "1914",
                        selectedVariant = ""
                    ),

                    QuestionModel(
                        question = "Buyuk Ipak yo’li qanday maqsadda ishlatilgan?",
                        variants = listOf("Tijorat", "Urush", "Sayohat", "Ilmiy izlanish"),
                        answer = "Tijorat",
                        selectedVariant = ""
                    ),
                    QuestionModel(
                        question = "Alisher Navoiy qaysi davlatda tug'ilgan?",
                        variants = listOf("Turkiston", "Movarounnahr", "Buxoro", "Xorazm"),
                        answer = "Movarounnahr",
                        selectedVariant = ""
                    ),
                    QuestionModel(
                        question = "Ikkinchi jahon urushi qaysi yilda yakunlangan?",
                        variants = listOf("1944", "1945", "1946", "1947"),
                        answer = "1945",
                        selectedVariant = ""
                    ),
                    QuestionModel(
                        question = "Rim imperiyasining qulashi qaysi yilda sodir bo'lgan?",
                        variants = listOf("410", "476", "525", "580"),
                        answer = "476",
                        selectedVariant = ""
                    ),
                    QuestionModel(
                        question = "G'arbiy Yevropada Uyg'onish davri qaysi asrda boshlangan?",
                        variants = listOf("12-asr", "13-asr", "14-asr", "15-asr"),
                        answer = "14-asr",
                        selectedVariant = ""
                    ),
                )
            }

            CategoryEnum.GEOGRAPHICAL -> {
                arrayListOf(
                    QuestionModel(
                        question = "Dunyodagi eng uzun daryo qaysi?",
                        variants = listOf("Nil", "Amazonka", "Yangtze", "Missisipi"),
                        answer = "Amazonka",
                        selectedVariant = ""
                    ),

                    QuestionModel(
                        question = "Yer yuzining eng katta cho'li qaysi?",
                        variants = listOf("Sahara", "Gobi", "Kalahari", "Arabiston"),
                        answer = "Sahara",
                        selectedVariant = ""
                    ),

                    QuestionModel(
                        question = "Qaysi davlat maydoni bo'yicha dunyodagi eng kichik davlat hisoblanadi?",
                        variants = listOf("Vatikan", "Monako", "San-Marino", "Lixtenshteyn"),
                        answer = "Vatikan",
                        selectedVariant = ""
                    ),

                    QuestionModel(
                        question = "Dunyodagi eng baland tog' cho'qqisi qaysi?",
                        variants = listOf("Everest", "K2", "Kilimanjaro", "Denali"),
                        answer = "Everest",
                        selectedVariant = ""
                    ),

                    QuestionModel(
                        question = "Afrika qit’asidagi eng katta ko'l qaysi?",
                        variants = listOf("Tanganika", "Viktoriya", "Malavi", "Chad"),
                        answer = "Viktoriya",
                        selectedVariant = ""
                    ),

                    QuestionModel(
                        question = "Qaysi mamlakat Yevrosiyo qit’alarida joylashgan?",
                        variants = listOf("Misr", "Turkiya", "Rossiya", "Germaniya"),
                        answer = "Rossiya",
                        selectedVariant = ""
                    ),
                    QuestionModel(
                        question = "Yer yuzidagi eng katta orol qaysi?",
                        variants = listOf("Grinlandiya", "Madagaskar", "Borneo", "Yangi Gvineya"),
                        answer = "Grinlandiya",
                        selectedVariant = ""
                    ),
                    QuestionModel(
                        question = "Okeanlar ichida eng katta bo'lgan okean qaysi?",
                        variants = listOf("Shimoliy muz okeani", "Atlantika okeani", "Hind okeani", "Tinch okeani"),
                        answer = "Tinch okeani",
                        selectedVariant = ""
                    ),

                    QuestionModel(
                        question = "Himolay tog'lari qaysi ikki davlat o'rtasida joylashgan?",
                        variants = listOf("Hindiston va Xitoy", "Nepal va Hindiston", "Butan va Hindiston", "Hindiston va Pokiston"),
                        answer = "Nepal va Hindiston",
                        selectedVariant = ""
                    ),

                    QuestionModel(
                        question = "Amazonka yomg'ir o'rmonlari asosan qaysi davlatda joylashgan?",
                        variants = listOf("Peru", "Braziliya", "Kolumbiya", "Venesuela"),
                        answer = "Braziliya",
                        selectedVariant = ""
                    ),

                    QuestionModel(
                        question = "Qaysi shahar 'Shamollar shahri' nomi bilan mashhur?",
                        variants = listOf("San-Fransisko", "Los-Anjeles", "Nyu-York", "Chikago"),
                        answer = "Chikago",
                        selectedVariant = ""
                    ),

                    QuestionModel(
                        question = "Qaysi davlatda eng ko’p ko’llar mavjud?",
                        variants = listOf("Rossiya", "Kanada", "AQSh", "Braziliya"),
                        answer = "Kanada",
                        selectedVariant = ""
                    ),

                    QuestionModel(
                        question = "Yer yuzining eng quruq joyi qaysi?",
                        variants = listOf("Atakama cho'li", "Sahara cho'li", "Arabiston cho'li", "Gobi cho'li"),
                        answer = "Atakama cho'li",
                        selectedVariant = ""
                    ),

                    QuestionModel(
                        question = "Qaysi mamlakatda eng katta inson populyatsiyasi mavjud?",
                        variants = listOf("Hindiston", "Xitoy", "AQSh", "Indoneziya"),
                        answer = "Xitoy",
                        selectedVariant = ""
                    ),

                    QuestionModel(
                        question = "Evropaning eng katta mamlakati qaysi?",
                        variants = listOf("Germaniya", "Fransiya", "Rossiya", "Ispaniya"),
                        answer = "Rossiya",
                        selectedVariant = ""
                    ),

                    QuestionModel(
                        question = "Dunyodagi eng uzun tog' tizmasi qaysi?",
                        variants = listOf("And tog'lari", "Himolay", "Qora tog'", "Appalachi tog'lari"),
                        answer = "And tog'lari",
                        selectedVariant = ""
                    ),

                    QuestionModel(
                        question = "Qaysi mamlakatda eng ko'p vulqonlar mavjud?",
                        variants = listOf("Indoneziya", "Yaponiya", "Meksika", "Italiya"),
                        answer = "Indoneziya",
                        selectedVariant = ""
                    ),

                    QuestionModel(
                        question = "Dunyodagi eng uzun daryo qaysi?",
                        variants = listOf("Nil", "Amazonka", "Missisipi", "Yangtze"),
                        answer = "Nil",
                        selectedVariant = ""
                    ),

                    QuestionModel(
                        question = "Qaysi davlat ikkita qit'ada joylashgan?",
                        variants = listOf("Turkiya", "Rossiya", "Misr", "Germaniya"),
                        answer = "Turkiya",
                        selectedVariant = ""
                    ),

                    QuestionModel(
                        question = "Afrikadagi eng baland tog' qaysi?",
                        variants = listOf("Kilimanjaro", "Kenya", "Simen", "Rwenzori"),
                        answer = "Kilimanjaro",
                        selectedVariant = ""
                    ),
                    QuestionModel(
                        question = "Qaysi daryoda eng katta sharshara mavjud?",
                        variants = listOf("Zambezi", "Amazonka", "Nil", "Missisipi"),
                        answer = "Zambezi",
                        selectedVariant = ""
                    ),
                    QuestionModel(
                        question = "Eng ko'p aholi yashaydigan shahar qaysi?",
                        variants = listOf("Tokio", "Nyu-York", "Delhi", "Shanxay"),
                        answer = "Tokio",
                        selectedVariant = ""
                    ),
                    QuestionModel(
                        question = "Yer yuzidagi eng baland sho'r ko'l qaysi?",
                        variants = listOf("Titikaka", "Aral", "Bolqash", "Marakibo"),
                        answer = "Titikaka",
                        selectedVariant = ""
                    ),
                    QuestionModel(
                        question = "Yer yuzidagi eng katta mamlakat qaysi?",
                        variants = listOf("Rossiya", "Kanada", "Xitoy", "AQSh"),
                        answer = "Rossiya",
                        selectedVariant = ""
                    ),
                    QuestionModel(
                        question = "Afrika qit’asidagi eng katta mamlakat qaysi?",
                        variants = listOf("Jazoir", "Sudan", "Kongo", "Liviya"),
                        answer = "Jazoir",
                        selectedVariant = ""
                    ),
                    QuestionModel(
                        question = "Yerdagi eng past nuqta qaysi?",
                        variants = listOf("O'lik dengiz", "Kaspiy dengizi", "Baliq ko'li", "Qizil dengiz"),
                        answer = "O'lik dengiz",
                        selectedVariant = ""
                    ),
                    QuestionModel(
                        question = "Qaysi davlatning poytaxti “Podsho shahri” deb tarjima qilinadi?",
                        variants = listOf("Kambodja", "Nepal", "Qozog'iston", "Misr"),
                        answer = "Kambodja",
                        selectedVariant = ""
                    ),
                    QuestionModel(
                        question = "Qaysi mamlakatda dunyodagi eng katta ko'rfaz joylashgan?",
                        variants = listOf("Hindiston", "AQSh", "Kanada", "Avstraliya"),
                        answer = "Kanada",
                        selectedVariant = ""
                    ),
                    QuestionModel(
                        question = "Qaysi davlat okean bilan o'ralgan orol davlat hisoblanadi?",
                        variants = listOf("Avstraliya", "Yangi Zelandiya", "Filippin", "Madagaskar"),
                        answer = "Avstraliya",
                        selectedVariant = ""
                    ),
                    QuestionModel(
                        question = "Eng katta davlatlar orasida eng kichik aholiga ega davlat qaysi?",
                        variants = listOf("Kanada", "Rossiya", "AQSh", "Avstraliya"),
                        answer = "Kanada",
                        selectedVariant = ""
                    ),
                    QuestionModel(
                        question = "Qaysi mamlakatni 'Ulkan Devlar Yeri' deb atashadi?",
                        variants = listOf("AQSh", "Kanada", "Avstraliya", "Xitoy"),
                        answer = "Xitoy",
                        selectedVariant = ""
                    ),
                    QuestionModel(
                        question = "Yer yuzidagi eng qalin yomg'ir o'rmoni qaysi davlatda joylashgan?",
                        variants = listOf("Braziliya", "Indoneziya", "Kongo", "Hindiston"),
                        answer = "Braziliya",
                        selectedVariant = ""
                    ),
                    QuestionModel(
                        question = "O'rta yer dengizidagi eng katta orol qaysi?",
                        variants = listOf("Sitsiliya", "Korsika", "Kipr", "Malta"),
                        answer = "Sitsiliya",
                        selectedVariant = ""
                    ),
                    QuestionModel(
                        question = "Qaysi mamlakatda 'Qor sheri' deb nomlangan tog'lar joylashgan?",
                        variants = listOf("Nepal", "Butan", "Shveytsariya", "Italiya"),
                        answer = "Nepal",
                        selectedVariant = ""
                    ),
                    QuestionModel(
                        question = "Yer yuzidagi eng katta qirg'oq chizig'iga ega davlat qaysi?",
                        variants = listOf("Kanada", "Rossiya", "Braziliya", "Indoneziya"),
                        answer = "Kanada",
                        selectedVariant = ""
                    ),
                    QuestionModel(
                        question = "Kavkaz tog'lari qaysi ikki qit'a o'rtasida joylashgan?",
                        variants = listOf("Yevropa va Osiyo", "Osiyo va Afrika", "Yevropa va Afrika", "Amerika va Osiyo"),
                        answer = "Yevropa va Osiyo",
                        selectedVariant = ""
                    ),
                    QuestionModel(
                        question = "Shimoliy Amerikadagi eng uzun daryo qaysi?",
                        variants = listOf("Missisipi", "Yukon", "Kolorado", "Amazonka"),
                        answer = "Missisipi",
                        selectedVariant = ""
                    ),
                    QuestionModel(
                        question = "Qaysi davlatda dunyodagi eng katta ko'llardan biri - Kaspiy dengizi joylashgan?",
                        variants = listOf("Rossiya", "Qozog'iston", "Ozarbayjon", "Turkmaniston"),
                        answer = "Rossiya",
                        selectedVariant = ""
                    ),
                    QuestionModel(
                        question = "Dunyodagi eng katta qirg'oq chizig'iga ega materik qaysi?",
                        variants = listOf("Antarktida", "Avstraliya", "Shimoliy Amerika", "Afrika"),
                        answer = "Antarktida",
                        selectedVariant = ""
                    ),
                    QuestionModel(
                        question = "Afrikadagi eng uzun daryo qaysi?",
                        variants = listOf("Nil", "Niger", "Kongo", "Zambezi"),
                        answer = "Nil",
                        selectedVariant = ""
                    ),
                )
            }
        }
}
