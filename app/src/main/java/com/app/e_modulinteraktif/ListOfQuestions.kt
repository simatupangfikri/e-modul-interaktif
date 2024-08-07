package com.app.e_modulinteraktif

const val MAX_NO_OF_QUESTIONS = 5
const val SCORE_INC = 20

val allQuestions: List<String> =
    listOf("1.\tDua kali umur Jasson ditambah umur ayahnya sekarang adalah 66 tahun. Sedangkan 3 tahun yang lalu selisih umur ayahnya dengan 3 kali umur Jasson adalah 7 tahun.Maka umur Jasson dan Ayah sekarang adalah….",
        "2.\tSelisih dua buah bilangan adalah 11. Jika dijumlahkan dua bilangan tersebut hasilnya adalah 63. Maka masing-masing bilangan tersebut adalah…..",
        "3.\tTiga keping disc merk A dan dua keeping disc merk B, harganya Rp 17.000,00 . Tiga keping disc merk A dan 10 keping disc merk B harganya Rp49.000,00 . Hitunglah harga satu keeping disc masing-masing merk.",
        "4.\tPerbandingan berat badan ayah dan anaknya 5 : 3 Badan ayah 20kg lebih berat dari badan anaknya. Hitunglah masing-masing berat badan ayah dan anak.",
        "5.\tDina membeli dua buah buku dan tiga buah pensil seharga ,- . Sedangkan Ira membeli tiga buah buku dan dua buah pensil seharga Berapakah harga masing - masing barang?",


        )

val allDescrip: List<String> =
    listOf("Misalkan \n" +
            "X = umur jasson \n" +
            "Y = umur ayah\n" +
            "2x + y = 66 ………………………….. persamaan (1)\n" +
            "(y – 3) – 3(x – 3) = 7\n" +
            "     y – 3 – 3x + 9 = 7\n" +
            "          -3x + y + 6 = 7\n" +
            "\t-3x + y   = 1 …………….. persamaan (2)\n" +
            "Eliminasi y\n" +
            "\t\t2x + y  = 66\n" +
            "\t\t-3x + y = 1   \n" +
            "\t\t       5x  = 65\n" +
            "\t\t         x  = 13\n" +
            "substitusi x = 13 ke persamaan (1)\n" +
            "\t\t     2x + y = 66\n" +
            "\t\t2(13) + y = 66\n" +
            "\t\t     26 + y = 66\n" +
            "\t\t              y = 40\n" +
            "Jadi umur Jasson sekarang adalah 13 tahun dan umur Ayah sekarang adalah 40 tahun. ",
        "x – y = 11 ………………………….. persamaan (1)\n" +
                "x + y = 63…………………………... persamaan (2)\n" +
                "Metode eliminasi:\n" +
                "x – y = 11\n" +
                "x + y = 63\n" +
                "    2y = 52\n" +
                "      y = 26\n" +
                "substitusi y = 26 ke persamaan (2)\n" +
                "x + y   = 63\n" +
                "x + 26 = 63\n" +
                "         x = 37\n" +
                "Jadi bilangan tersebut adalah 26 dan 37\n",
        "Misalkan \n" +
                "X = disc merk A \n" +
                "Y = disc merk B\n" +
                "3x – 2y   = 17.000 ………………………….. persamaan (1)\n" +
                "3x + 10y = 49.000…………………………... persamaan (2)\n" +
                "Eliminasi x\n" +
                "3x – 2y   = 17.000\n" +
                "3x + 10y = 49.000\n" +
                "          -8y = -32.000\n" +
                "           8y = 32.000\n" +
                "              y = 4.000\n" +
                "substitusi y = 4.000 ke persamaan (1)\n" +
                "3x – 2y            = 17.000\n" +
                "3x – 2(4.000)  = 17.000\n" +
                "3x – 8.000       = 17.000\n" +
                "     3x   = 9.000\n" +
                "         x = 3.000\n" +
                "Jadi, harga satu keeping disc merk A = Rp 3.000 dan harga satu keeping disc merk B = Rp 4.000\n",
        "Misalkan\n" +
                "X = berat ayah \n" +
                "Y = berat anak\n" +
                "x : y = 5 : 3\n" +
                " = \n" +
                "3x = 5y ………………………….. persamaan (1)\n" +
                "x – 20 = y\n" +
                "x – y = 20 ………………..…….. persamaan (2)\n" +
                "substitusi 3x = 5y \n" +
                "x – y = 20   3  3y- 3x = 60\n" +
                "\t\t    5y- 3x =   0\n" +
                "\t\t\t           2y = 60\n" +
                "\t\t\t\ty = 30\n" +
                "substitusi y ke persamaan (1)\n" +
                "3x = 5y\n" +
                "3x = 5(30)\n" +
                "3x = 150\n" +
                "  x = 50\n" +
                "Jadi berat badan ayah sebesar 50 kg dan berat badan anak sebesar 30kg\n",
        "Misalkan\n" +
                "X = buku\n" +
                "Y = pensil\n" +
                "2x + 3y = 9.000 ………………………….. persamaan (1)\n" +
                "3x + 2y = 10.000………………………….. persamaan (2)\n" +
                "Eliminasi x\n" +
                "2x + 3y = 9.000     3  6x + 9y = 27.000\n" +
                "3x + 2y = 10.000   2  6x + 4y = 20.000\n" +
                "\t\t\t            5y = 7.000\n" +
                "\t\t\t              y = 1.400\n" +
                "substitusi y = 1.400 ke persamaan (1)\n" +
                "           2x + 3y = 9.000\n" +
                "2x + 3(1.400) = 9.000\n" +
                "     2x + 4.200 = 9.000\n" +
                " \t      2x = 4.800\n" +
                "\t        x = 2.400\n" +
                "Jadi harga satu buah buku adalah Rp.2.400 dan harga satu pensil adalah Rp. 1.400\n",


    )

val allAnswers: List<List<String>> =
    listOf(
        listOf("a. jasson 13 tahun dan ayah 40 tahun",
                "b. jasson 15 tahun dan ayah 35 tahun",
               "c. jasson 14 tahun dan ayah 41 tahun",
               "d. jasson 16 tahun dan ayah 40 tahun"
        ),
        listOf("a. 20 dan 40",
                "b. 22 dan 31",
                "c. 26 dan 37",
               "d. 25 dan 35"
        ),
        listOf("a. A = Rp.6000 dan B = Rp.5000",
                "b. A = Rp.3000 dan B = Rp.4000",
                "c. A = Rp.4000 dan B = Rp.3000",
               "d. A = Rp.5000 dan B = Rp.4000"
        ),
        listOf("a. Ayah 50kg dan anak 20kg",
                "b. Ayah 60kg dan anak 40kg",
                "c. Ayah 60kg dan anak 30kg",
                "d. Ayah 50kg dan anak 30kg"
        ),
        listOf("a. Buku = Rp. 2.400 dan pensil = Rp. 1.400",
                "b. Buku = Rp. 3.000 dan pensil = Rp. 2.400",
                "c. Buku = Rp. 4.500 dan pensil = Rp. 1.500",
                "d. Buku = Rp. 5.000 dan pensil = Rp. 3.000"
        ),

    )

val keyAnswers: List<String> =
    listOf("A", "C", "B", "D", "A")