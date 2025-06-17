package com.example.tpraktikum4_h071231079;

import java.util.ArrayList;
import java.util.List;

public class BookRepository {
    public static final List<Book> books = new ArrayList<>();

    public static List<Book> getBooks() {
        if (books.isEmpty()) {

            books.add(new Book(1, "Closer to Nowhere", "Ellen Hopkins", 2020,
                    "Clara dan Riley tumbuh di bawah atap keluarga yang penuh rahasia dan ketegangan. " +
                            "Mereka berdua mencoba melarikan diri dari bayang-bayang masa lalu yang membebani hidup mereka. " +
                            "Setiap halaman menampilkan konflik batin yang intens, menyoroti bagaimana trauma dapat membentuk pilihan remaja. " +
                            "Hubungan saudara kembar ini diuji ketika rahasia keluarga mulai terkuak satu per satu. " +
                            "Dengan gaya narasi puitis, Ellen Hopkins menanamkan harapan di tengah keputusasaan.",
                    "Young Adult", 4.5f,
                    new String[]{
                            "Ellen Hopkins menggambarkan konflik keluarga dengan puitis dan emosional.",
                            "Sebuah novel remaja yang menyayat hati dan menyentuh sisi kemanusiaan.",
                            "Karakter-karakternya kuat dan realistis, membuat pembaca ikut terbawa suasana."
                    }, R.drawable.book1));

            books.add(new Book(2, "Once upon a Curfew", "Srishti Chaudhary", 2019,
                    "Berlatar India pada tahun 1970-an, novel ini memadukan kisah cinta dengan gejolak politik. " +
                            "Aria, tokoh utama, berusaha menyeimbangkan impian pribadinya dengan harapan keluarga. " +
                            "Latar kota yang dilanda jam malam menjadi saksi bisu konflik batin dan pertaruhan hati. " +
                            "Srishti Chaudhary menulis dengan gaya yang ringan namun kaya akan nuansa historis. " +
                            "Setiap bab menuntun pembaca masuk lebih dalam ke atmosfer nostalgia dan romantisme klasik.",
                    "Romance", 4.2f,
                    new String[]{
                            "Romansa klasik dengan nuansa sejarah yang memukau.",
                            "Karakter utama sangat relatable, jalan ceritanya ringan tapi menggugah.",
                            "Atmosfer India era 1970-an dihadirkan dengan detil yang akurat."
                    }, R.drawable.book2));

            books.add(new Book(3, "Again, But Better", "Christine Riccio", 2019,
                    "Andi memutuskan memulai hidup baru dengan mendaftar kuliah di London setelah mengalami patah hati. " +
                            "Di sana ia menghadapi tantangan budaya, pertemanan, dan pencarian jati diri. " +
                            "Novel ini menyajikan humor segar saat Andi berusaha bangkit dari kegagalan cinta masa lalu. " +
                            "Pembaca disuguhi kisah coming-of-age yang hangat dan penuh semangat petualangan. " +
                            "Setiap momen perjuangan Andi terasa autentik dan inspiratif bagi siapa saja yang ingin memulai kembali.",
                    "Young Adult", 4.0f,
                    new String[]{
                            "Cerita coming-of-age yang hangat, penuh humor dan harapan.",
                            "Gaya bahasa ringan namun menyentuh, cocok untuk pembaca muda.",
                            "Mengajarkan arti keberanian memulai lembaran baru."
                    }, R.drawable.book3));

            books.add(new Book(4, "A Study in Drowning", "Ava Reid", 2023,
                    "Ketika arsitek muda Lenora menemukan manuskrip tak dikenal milik penulis favoritnya, misteri pun bermula. " +
                            "Ia terseret ke dalam dunia gelap di balik kata-kata yang tak pernah dipublikasikan. " +
                            "Atmosfer dark academia semakin pekat saat rahasia kelam penulis itu terungkap satu per satu. " +
                            "Ava Reid memadukan elemen fantasi dan thriller dengan sangat rapi, menghadirkan ketegangan tiada henti. " +
                            "Setiap bab terasa seperti labirin emosional yang menantang pembaca untuk menebak apa yang akan terjadi selanjutnya.",
                    "Fantasy", 4.7f,
                    new String[]{
                            "Latar dark academia berpadu elemen magis dengan sangat memikat.",
                            "Plot penuh teka-teki, menantang pembaca berpikir kritis.",
                            "Ava Reid berhasil menciptakan atmosfer yang dens dan menegangkan."
                    }, R.drawable.book4));

            books.add(new Book(5, "The Wind Rises", "Hayao Miyazaki", 2009,
                    "Dalam fiksi biografi ini, kita diajak menyusuri impian Jiro, seorang insinyur pesawat terbang. " +
                            "Cerita bergulir dari masa kecilnya yang penuh kekaguman pada burung terbang hingga kariernya di industri penerbangan. " +
                            "Miyazaki menyelipkan puisi tentang embun pagi dan sayap baja dengan harmoni yang memukau. " +
                            "Narasi sejarah teknis dibalut dengan sentuhan emosional, membuat fakta terasa hidup. " +
                            "Buku ini menjadi penghormatan pada kreativitas dan semangat para pendiri pesawat modern.",
                    "Biography", 4.6f,
                    new String[]{
                            "Memoar fiksi yang memadukan fakta sejarah dengan narasi puitis.",
                            "Menggugah semangat pembaca untuk bermimpi dan berinovasi.",
                            "Detail teknis penerbangan disajikan dengan cara yang mudah dipahami."
                    }, R.drawable.book5));

            books.add(new Book(6, "The Woman in the Window", "A. J. Finn", 2018,
                    "Anna terkurung dalam ketakutan agorafobia sejak kecelakaan yang merenggut keluarganya. " +
                            "Ia mengamati tetangganya melalui jendela sambil meneguk pil penenang. " +
                            "Segalanya berubah saat ia menyaksikan kejadian mengerikan yang justru tak ada buktinya. " +
                            "A. J. Finn membangun ketegangan psikologis dengan cermat, menanamkan keraguan di setiap halaman. " +
                            "Plot twist di akhir berhasil membuat pembaca terkejut dan terkesima.",
                    "Mystery", 4.3f,
                    new String[]{
                            "Ketegangan psikologis dibangun secara perlahan tapi pasti.",
                            "Twist di akhir cerita memukau dan tak terduga.",
                            "Narasi unreliable narrator menambah lapisan misteri yang menarik."
                    }, R.drawable.book6));

            books.add(new Book(7, "When Breath Becomes Air", "Paul Kalanithi", 2016,
                    "Paul, seorang ahli bedah saraf, tak pernah membayangkan hidupnya akan berubah drastis saat didiagnosis kanker. " +
                            "Memoarnya menyajikan refleksi mendalam tentang kehidupan, kematian, dan makna keduanya. " +
                            "Setiap bab meresap dengan kejujuran yang menusuk, menghadirkan pertanyaan filosofis yang kuat. " +
                            "Gaya penulisan Kalanithi yang sederhana justru membuat pesan lebih kuat dan otentik. " +
                            "Buku ini menjadi catatan indah tentang keberanian menghadapi akhir hidup.",
                    "Biography", 4.9f,
                    new String[]{
                            "Kisah yang sangat pribadi dan menyentuh, ditulis dengan kejujuran penuh.",
                            "Mengajarkan perspektif baru tentang arti hidup dan kematian.",
                            "Struktur narasi yang menyatu dengan refleksi filosofis membuatnya tak terlupakan."
                    }, R.drawable.book7));

            books.add(new Book(8, "The Cat Who Saved Books", "Sosuke Natsukawa", 2021,
                    "Rintaro mewarisi kucing misterius yang memiliki misi menyelamatkan buku-buku terlupakan. " +
                            "Bersama kucingnya, ia menjelajah toko buku tua dan menemukan kisah-kisah yang hampir hilang. " +
                            "Setiap petualangan diwarnai filosofi ringan tentang arti penting membaca. " +
                            "Narasi disajikan dengan sentuhan magis yang menenangkan hati pembaca. " +
                            "Sosuke Natsukawa mengingatkan kita bahwa buku adalah jendela dunia yang layak dipertahankan.",
                    "Fantasy", 4.4f,
                    new String[]{
                            "Sentuhan magis pada tema kecintaan pada buku terasa sangat hangat.",
                            "Bahasa yang mengalir lembut, penuh metafora yang indah.",
                            "Karakter kucingnya unik dan memberikan warna tersendiri pada cerita."
                    }, R.drawable.book8));

            books.add(new Book(9, "Little Women", "Louisa May Alcott", 1868,
                    "Empat saudari March menghadapi suka duka masa kecil mereka di New England. " +
                            "Mereka belajar tentang cinta, kehilangan, dan tanggung jawab satu sama lain. " +
                            "Jo, Meg, Beth, dan Amy memperlihatkan kekuatan perempuan dalam berbagai cara. " +
                            "Alcott menyuguhkan kisah keluarga dengan perpaduan hangat dan kritik sosial ringan. " +
                            "Novel ini tetap relevan sebagai inspirasi bagi pembaca lintas generasi.",
                    "Young Adult", 4.8f,
                    new String[]{
                            "Klasik sastra yang kaya akan nilai kehangatan keluarga dan persaudaraan.",
                            "Karakter Jo March menjadi simbol girl power yang timeless.",
                            "Alcott menyajikan kisah dengan keseimbangan sempurna antara drama dan harapan."
                    }, R.drawable.book9));

            books.add(new Book(10, "Women Talking", "Miriam Toews", 2018,
                    "Sekelompok perempuan di komunitas tertutup berkumpul untuk mendiskusikan masa depan mereka. " +
                            "Mereka berbicara tentang keadilan, hak, dan kebebasan dalam kerangka tradisi agama yang ketat. " +
                            "Dialog antar tokoh menampilkan kekuatan kolektif dan kerentanan individu secara berimbang. " +
                            "Miriam Toews menulis dengan gaya lugas, menyodorkan kritik sosial tanpa terdengar menghakimi. " +
                            "Cerita ini mengajak pembaca merenung tentang suara perempuan di berbagai belahan dunia.",
                    "Drama", 4.5f,
                    new String[]{
                            "Diskusi gender dan kekuasaan dikemas dengan dialog yang tajam.",
                            "Menawarkan pandangan kritis terhadap struktur sosial patriarkal.",
                            "Gaya bertutur yang lugas namun sarat makna memikat pembaca."
                    }, R.drawable.book10));

            // 11 – 20

            books.add(new Book(11, "Twisted Love", "Ana Huang", 2021,
                    "Nina terjebak dalam pusaran emosi saat membantu teman lamanya menghadapi tragedi keluarga. " +
                            "Konflik batin muncul ketika janji masa lalu dan rahasia keluarga berbenturan. " +
                            "Setting kota kosmopolitan memperkaya dinamika hubungan antarkarakter. " +
                            "Ana Huang memadukan ketegangan romance dengan perkembangan karakter yang matang. " +
                            "Novel ini menggugah pertanyaan tentang batas antara cinta dan tanggung jawab.",
                    "Romance", 4.3f,
                    new String[]{
                            "Alur cerita yang otentik menangkap kompleksitas emosi modern dengan baik.",
                            "Pengembangan karakter yang mendalam membuat konflik terasa hidup.",
                            "Gaya penulisan luwes memudahkan pembaca terhubung secara emosional."
                    }, R.drawable.book11));

            books.add(new Book(12, "The Girls I've Been Given", "Tess Sharpe", 2022,
                    "Penulis muda Riley kembali ke kampung halamannya untuk mencari inspirasi menulis. " +
                            "Blok kreatifnya terurai saat ia menghadapi kenangan masa kecil penuh warna. " +
                            "Tess Sharpe membangun suasana nostalgia yang manis sekaligus pahit. " +
                            "Narasi puitis berpadu humor halus, menyeimbangkan emosi pembaca. " +
                            "Tema ingatan dan identitas diolah dengan kedalaman yang mengesankan.",
                    "Young Adult", 4.2f,
                    new String[]{
                            "Penggunaan bahasa puitis memperkaya tema ingatan dan identitas.",
                            "Humor halus berpadu ketegangan emosional dalam keseimbangan yang pas.",
                            "Struktur naratifnya memikat, mengajak pembaca refleksi tanpa kehilangan alur."
                    }, R.drawable.book12));

            books.add(new Book(13, "Nobody Knows But You", "Anica Mrose Rissi", 2021,
                    "Ketika Lana menemukan surat rahasia di loteng, masa lalunya pun terbongkar. " +
                            "Ia dipertemukan lagi dengan sahabat lamanya yang membawa kenangan pahit. " +
                            "Anica Mrose Rissi menyajikan konflik persahabatan dengan nuansa emosi yang mendalam. " +
                            "Setiap dialog terasa autentik, menggambarkan kerentanan dan harapan. " +
                            "Plot twist terstruktur baik, menjaga ketegangan hingga halaman terakhir.",
                    "Mystery", 4.1f,
                    new String[]{
                            "Twist plot terstruktur rapi, memancing penasaran tanpa mengorbankan logika.",
                            "Penggambaran persahabatan terasa otentik dan penuh nuansa emosional.",
                            "Bahasa yang lugas memudahkan imersi pembaca ke dalam konflik karakter."
                    }, R.drawable.book13));

            books.add(new Book(14, "You've Reached Sam", "Dustin Thao", 2021,
                    "Mia terpukul saat pacarnya, Sam, meninggal mendadak sebelum mereka sempat berbaikan. " +
                            "Ia mulai menelusuri voicemail lama, seolah mendapat kesempatan bicara sekali lagi. " +
                            "Cerita ini menyelami proses berduka dengan sensitif dan penuh harapan. " +
                            "Dustin Thao mengeksplorasi tema kehilangan tanpa berlebihan dramatis. " +
                            "Hasilnya adalah kisah yang menenangkan, memberi ruang untuk penerimaan dan kenangan manis.",
                    "Romance", 4.4f,
                    new String[]{
                            "Konsep voicemail sebagai medium cerita memberikan orisinalitas yang kuat.",
                            "Tema kehilangan dan penerimaan diolah dengan kepekaan tinggi.",
                            "Dialog natural memperkuat ikatan emosional antara karakter dan pembaca."
                    }, R.drawable.book14));

            books.add(new Book(15, "Girls of July", "Alex Flinn", 2011,
                    "Sepuluh gadis terpilih dikirim ke pulau terpencil untuk eksperimen sosial rahasia. " +
                            "Mereka harus bekerja sama meski dipisahkan oleh perbedaan latar belakang. " +
                            "Alex Flinn mengolah dinamika kelompok dengan ketegangan yang terus meningkat. " +
                            "Isolasi pulau menambah tekanan psikologis, memunculkan konflik dan aliansi baru. " +
                            "Novel ini memadukan unsur misteri dan drama remaja secara seimbang.",
                    "Young Adult", 4.0f,
                    new String[]{
                            "Dinamika kelompok diuraikan dengan detil, menciptakan ketegangan yang menggugah.",
                            "Karakter-karakter diberi ruang perkembangan yang seimbang.",
                            "Latar pulau terpencil memperkuat suasana misteri dan isolasi."
                    }, R.drawable.book15));

            books.add(new Book(16, "Little Do You Know", "Laura J. Matthews", 2020,
                    "Miles dan Charlie menjalin hubungan jarak jauh setelah pertemuan singkat di konferensi. " +
                            "Mereka menghadapi tantangan komunikasi dan kerinduan yang mendalam. " +
                            "Laura J. Matthews menulis dengan gaya yang manis dan penuh empati. " +
                            "Alur cerita mengalir alami, membawa pembaca merasakan tiap detik kerinduan. " +
                            "Ini adalah kisah tentang harapan dan janji yang tetap hidup meski terpisah jarak.",
                    "Romance", 4.2f,
                    new String[]{
                            "Narasi hubungan jarak jauh digambarkan dengan nuansa kerinduan yang otentik.",
                            "Alur mengalir lancar, membuat pembaca mudah terhanyut emosinya.",
                            "Deskripsi emosi tokoh utama tertata rapi tanpa berlebihan."
                    }, R.drawable.book16));

            books.add(new Book(17, "Days of Awe", "Lauren Fox", 2021,
                    "Jurnalis Ellie kembali ke kota kecil kelahirannya untuk menyelidiki hilangnya penduduk. " +
                            "Setiap wawancara menyingkap lapisan gelap sejarah keluarga dan kota tersebut. " +
                            "Atmosfer misteri membangun ketegangan sejak halaman pertama. " +
                            "Lauren Fox memadukan adegan investigasi dengan momen reflektif yang tajam. " +
                            "Novel ini merupakan keahlian psikologis yang membuat pembaca terus menebak hingga akhir.",
                    "Thriller", 4.5f,
                    new String[]{
                            "Pacing yang cermat menjaga ketegangan sepanjang cerita.",
                            "Atmosfer misteri dibangun dengan detail, memancing rasa ingin tahu.",
                            "Karakter protagonis menghadirkan kedalaman psikologis yang menarik."
                    }, R.drawable.book17));

            books.add(new Book(18, "Not Here to Be Liked", "Michelle Quach", 2020,
                    "Nina memulai tahun pertamanya di universitas yang megah dengan tekad tinggi. " +
                            "Ia berjuang menyeimbangkan prestasi akademik dan persahabatan sejati. " +
                            "Michelle Quach menampilkan dinamika kampus dengan observasi yang segar. " +
                            "Konflik internal Nina terasa realistis, mencerminkan dilema banyak mahasiswa baru. " +
                            "Gaya bercerita ringan namun bermakna membuat novel ini mudah dinikmati siapa saja.",
                    "Young Adult", 4.1f,
                    new String[]{
                            "Observasi kehidupan kampus disajikan dengan keakuratan dan humor halus.",
                            "Dilema karakter utama dirangkai realistis, mencerminkan tantangan mahasiswa.",
                            "Gaya narasi ringan namun bermakna memikat pembaca lintas usia."
                    }, R.drawable.book18));

            books.add(new Book(19, "They Both Die at the End", "Adam Silvera", 2017,
                    "Di sebuah layanan misterius, Mateo dan Rufus menerima pemberitahuan bahwa hari ini adalah hari terakhir mereka. " +
                            "Mereka memutuskan untuk menjalani hari itu seakan ini adalah hari yang paling berarti. " +
                            "Adam Silvera mengeksplorasi tema kematian, persahabatan, dan cinta dengan kepekaan tinggi. " +
                            "Narasi bergantian sudut pandang menambah kedalaman emosional setiap karakter. " +
                            "Novel ini menjadi refleksi indah tentang bagaimana kita memilih untuk hidup meski waktu terbatas.",
                    "Young Adult", 4.6f,
                    new String[]{
                            "Eksplorasi tema kematian dan makna hidup dikemas dengan kepekaan tinggi.",
                            "Karakterisasi kuat membuat pembaca merasakan intensitas emosi tokoh.",
                            "Struktur narasi inovatif memperkuat kedalaman cerita."
                    }, R.drawable.book19));

            books.add(new Book(20, "Things We Never Go Over", "Lucy Score", 2021,
                    "Jane dan Tom, mantan teman sekelas, terpaksa bekerja sama untuk menyelamatkan proyek seni sekolah. " +
                            "Ketegangan lama dan kepedulian baru mulai muncul di antara mereka. " +
                            "Lucy Score memadukan humor manis dan ketegangan ringan dalam setiap adegan. " +
                            "Dialog-tajamnya memperlihatkan chemistry yang tumbuh perlahan namun pasti. " +
                            "Buku ini mengajarkan bahwa kadang kita harus melewati masa lalu untuk mencapai masa depan bersama.",
                    "Romance", 4.3f,
                    new String[]{
                            "Interaksi antar karakter diuraikan dengan detail yang otentik.",
                            "Konflik dan resolusi disajikan dengan tempo seimbang, menawarkan hiburan plus kedalaman.",
                            "Dialog tajam dan deskripsi terukur membuat alur mudah diikuti."
                    }, R.drawable.book20));

            books.add(new Book(21, "The Midnight Library", "Matt Haig", 2020,
                    "Nora Seed menemukan sebuah perpustakaan ajaib di antara hidup dan mati, di mana setiap buku adalah kehidupan alternatif yang bisa ia jalani. " +
                            "Setiap kali ia membuka satu buku, Nora menjejak ke realita baru yang tampak sempurna, hingga ia menyadari konsekuensi pilihan. " +
                            "Melalui perjalanan metaforis, novel ini mengeksplorasi penyesalan dan arti kebahagiaan sejati. " +
                            "Gaya penulisan Haig yang hangat mengundang pembaca merenung tentang arti hidup mereka sendiri. " +
                            "Sebuah kisah inspiratif untuk siapa pun yang pernah merasa terjebak pada satu jalan hidup.",
                    "Fantasy", 4.3f,
                    new String[]{
                            "Sebuah alegori indah tentang penyesalan dan harapan yang universal.",
                            "Narasi mengalir lembut namun memukul hati, membuat pembaca berpikir ulang soal pilihan hidup.",
                            "Karakter Nora sangat relatable, perjalanan emosionalnya menyentuh."
                    }, R.drawable.book27));

            books.add(new Book(22, "Project Hail Mary", "Andy Weir", 2021,
                    "Ryland Grace terbangun sendirian di pesawat luar angkasa dengan ingatan yang samar, ia harus menyelamatkan Bumi dari kehancuran. " +
                            "Dalam perjalanan ilmiah yang menegangkan, Grace bekerja sama dengan makhluk asing untuk menemukan solusi. " +
                            "Weir menghadirkan sains yang detil namun tetap mudah diikuti, penuh momen humor di tengah krisis. " +
                            "Ketegangan berpadu dengan kehangatan persahabatan lintas spesies. " +
                            "Novel ini adalah perpaduan sempurna antara hard sci-fi dan cerita survival.",
                    "Science Fiction", 4.6f,
                    new String[]{
                            "Plotnya cerdas dan penuh twist sains yang membuat jantung berdebar.",
                            "Humornya pas, mencerahkan di antara babak-babak penuh teori fisika.",
                            "Hubungan antar tokoh lintas planet terasa tulus dan mengharukan."
                    }, R.drawable.book24));

            books.add(new Book(23, "The Invisible Life of Addie LaRue", "V.E. Schwab", 2020,
                    "Addie LaRue membuat perjanjian dengan iblis agar bisa hidup selamanya, namun dilupakan siapa pun yang mengenalnya. " +
                            "Selama ratusan tahun, ia berjalan di masa lalu hingga modern, menorehkan jejak samar pada sejarah. " +
                            "Hingga suatu hari, seseorang mengingat namanya—dan kesepakatan kelam itu kembali menghantuinya. " +
                            "Schwab menulis dengan prosa puitis yang memikat, memadukan fantasi gelap dan romansa. " +
                            "Cerita ini menanyakan apa arti keabadian tanpa kenangan.",
                    "Fantasy", 4.2f,
                    new String[]{
                            "Gaya bahasanya mengalir seperti puisi, menawan sejak halaman pertama.",
                            "Konsep lupa-abadi yang orisinal dan mengharukan.",
                            "Karakter Addie kuat, narasinya penuh emosi dan misteri."
                    }, R.drawable.book22));

            books.add(new Book(24, "The Vanishing Half", "Brit Bennett", 2020,
                    "Kembar identik Stella dan Desiree berpisah jalan, satu memilih hidup sebagai kulit putih, satunya lagi kembali ke kampung halaman. " +
                            "Novel ini menggali isu ras, identitas, dan kebenaran keluarga di Amerika modern. " +
                            "Bennett meramu cerita lintas generasi dengan karakter yang kompleks dan beragam. " +
                            "Setiap keputusan memicu konsekuensi besar bagi dirinya dan anak-anak mereka. " +
                            "Sebuah kisah tajam tentang asal-usul dan pilihan hidup.",
                    "Drama", 4.1f,
                    new String[]{
                            "Pemaparan isu ras dan identitas disajikan dengan jujur dan kuat.",
                            "Alur lintas generasi memperkaya narasi dengan lapisan emosi.",
                            "Karakter kembar yang berbeda jalan hidupnya sangat menarik untuk diikuti."
                    }, R.drawable.book29));

            books.add(new Book(25, "Circe", "Madeline Miller", 2018,
                    "Dalam adaptasi epik mitologi Yunani ini, Circe, putri Helios, menemukan kekuatan sihirnya dan diasingkan di pulau terpencil. " +
                            "Ia berinteraksi dengan dewa, monster, dan pahlawan legendaris, menorehkan kisahnya sendiri. " +
                            "Miller menulis ulang mitos dengan sudut pandang feminis yang segar dan intens. " +
                            "Perjalanan Circe tentang kekuatan, cinta, dan kebebasan membius pembaca. " +
                            "Sebuah novel yang memadukan fantasi kuno dengan narasi emosional modern.",
                    "Fantasy", 4.4f,
                    new String[]{
                            "Retelling mitologi yang kaya warna dan penuh detil menakjubkan.",
                            "Karakter Circe ditulis dengan kedalaman emosional dan kekuatan narasi.",
                            "Bahasanya puitis, membangun dunia dewa-dewi dengan sempurna."
                    }, R.drawable.book26));

            books.add(new Book(26, "Where the Crawdads Sing", "Delia Owens", 2018,
                    "Kya Clark, gadis liar di rawa, tumbuh sendiri setelah keluarga meninggalkannya. " +
                            "Ia belajar bertahan hidup dengan alam, hingga sebuah kematian misterius mengguncang kota kecil. " +
                            "Novel ini menyelipkan kisah coming-of-age, misteri pembunuhan, dan romansa. " +
                            "Owens menggambarkan alam rawa dengan detail indah dan kuat. " +
                            "Sebuah ode pada kesendirian, cinta, dan ketahanan manusia.",
                    "Mystery", 4.5f,
                    new String[]{
                            "Setting rawa digambarkan hidup, membuat pembaca seolah terbenam di alam.",
                            "Campuran misteri dan coming-of-age terasa seimbang dan menyentuh.",
                            "Karakter Kya sangat kuat, perjuangannya menawan hati."
                    }, R.drawable.book25));

            books.add(new Book(27, "The Song of Achilles", "Madeline Miller", 2011,
                    "Patroclus membangun ikatan mendalam dengan Achilles sejak masa kecil, hingga keduanya terlibat dalam Perang Troya. " +
                            "Miller meramu kisah cinta dan persahabatan dalam mitos Yunani kuno. " +
                            "Narasi penuh emosi menggambarkan sisi kemanusiaan para pahlawan legendaris. " +
                            "Setiap babak penuh kekecewaan dan haru, menyoroti harga kehormatan dan cinta. " +
                            "Sebuah karya epik yang memadukan mitologi dan romansa dengan apik.",
                    "Fantasy", 4.2f,
                    new String[]{
                            "Retelling epik Perang Troya penuh emosi dan kedalaman karakter.",
                            "Hubungan Patroclus–Achilles digambarkan dengan sangat tulus dan menyentuh.",
                            "Bahasanya indah, membuat mitos kuno terasa segar dan hidup."
                    }, R.drawable.book23));

            books.add(new Book(28, "Educated", "Tara Westover", 2018,
                    "Memoar Tara Westover tentang tumbuh di keluarga Mormon ekstrem dan perjuangannya menuntut ilmu. " +
                            "Tanpa ijazah sekolah, ia belajar sendiri hingga meraih beasiswa di Cambridge University. " +
                            "Westover menulis dengan kejujuran brutal tentang kekerasan keluarga dan kebebasan pikiran. " +
                            "Perjalanan akademisnya menjadi simbol keteguhan dan keberanian. " +
                            "Sebuah kisah inspiratif tentang kekuatan pendidikan dan identitas.",
                    "Biography", 4.7f,
                    new String[]{
                            "Kisah nyata yang menginspirasi tentang kekuatan pendidikan.",
                            "Penggambaran konflik keluarga terasa sangat intens dan jujur.",
                            "Memoar yang memompa semangat dan refleksi diri."
                    }, R.drawable.book28));

            books.add(new Book(29, "Normal People", "Sally Rooney", 2018,
                    "Connell dan Marianne menjalin hubungan rumit sejak sekolah menengah hingga kuliah di Trinity College. " +
                            "Rooney menelusuri dinamika cinta, kekuasaan, dan kerentanan di antara dua pribadi berbeda. " +
                            "Gaya dialog minimalis namun penuh makna menghidupkan konflik emosional mereka. " +
                            "Novel ini reflektif tentang identitas dan hubungan manusia modern. " +
                            "Sebuah kisah sederhana yang menggugah dan intens.",
                    "Drama", 4.0f,
                    new String[]{
                            "Dialog Rooney begitu tajam dan realistis, memotret emosi tersirat.",
                            "Hubungan Connell–Marianne kompleks dan menarik untuk diikuti.",
                            "Analisis psikologis karakter terasa mendalam meski gaya minimalis."
                    }, R.drawable.book30));

            books.add(new Book(30, "The Night Circus", "Erin Morgenstern", 2011,
                    "Sirkus misterius muncul tanpa peringatan, beroperasi hanya di malam hari, dipenuhi atraksi magis. " +
                            "Dua pesulap muda terjebak dalam kompetisi sihir yang mempertaruhkan hidup mereka. " +
                            "Morgenstern menciptakan atmosfer fantasi yang memikat, penuh warna dan nada misteri. " +
                            "Cinta terlarang muncul di tengah permainan berbahaya. " +
                            "Novel ini adalah pesta indra yang memukau dengan narasi visual yang kuat.",
                    "Fantasy", 4.4f,
                    new String[]{
                            "Deskripsi sirkus sangat vivid, membawa pembaca masuk ke dunia magis.",
                            "Plot berputar di sekitar cinta dan konflik yang memikat.",
                            "Gaya narasi puitis menciptakan suasana yang mendalam dan tak terlupakan."
                    }, R.drawable.book21));

            books.add(new Book(31, "The Nightingale", "Kristin Hannah", 2015,
                    "Di Prancis yang diduduki Jerman selama Perang Dunia II, dua bersaudara —Vianne dan Isabelle—menghadapi perang dengan cara berbeda. " +
                            "Vianne tetap di rumah, berjuang melindungi putrinya dan tetangga dari kekejaman tentara pendudukan, sambil menyembunyikan pilot Sekutu yang terluka. " +
                            "Isabelle, gadis pemberontak, bergabung dengan jaringan perlawanan bawah tanah, menyelundupkan dokumen, tentara, dan harapan menuju kebebasan. " +
                            "Novel ini menyoroti keberanian wanita biasa yang melakukan tindakan luar biasa dalam kegelapan sejarah, memperlihatkan kasih sayang, pengorbanan, dan kekuatan cinta di saat-saat tergelap.",
                    "Historical Fiction", 4.7f,
                    new String[]{
                            "Deskripsi detail Prancis pedesaan dan kota-kota kecil di bawah pendudukan membuat suasana perang terasa hidup dan menegangkan.",
                            "Vianne dan Isabelle ditulis dengan karakter yang kuat dan realistis—penuh konflik batin, keberanian, dan keraguan manusiawi.",
                            "Penggambaran jaringan perlawanan bawah tanah menyajikan ketegangan tak terduga, dengan momen-momen haru yang menguras air mata.",
                            "Kristin Hannah menenun narasi emosional yang memukau, menegaskan pentingnya solidaritas dan harapan di tengah kehancuran."
                    }, R.drawable.book34));

            books.add(new Book(32, "Lessons in Chemistry", "Bonnie Garmus", 2022,
                    "Elisabeth Zott, ilmuwan jenius di era 1950-an, menghadapi diskriminasi gender sambil memimpin acara memasak di televisi. " +
                            "Lewat eksperimen kulinernya, ia menyelipkan pelajaran kimia dan pemberdayaan perempuan, menantang norma sosial yang mengekang. " +
                            "Di balik set dapur, Elisa membuktikan bahwa sains dapat diakses dan menyenangkan, sambil melawan prasangka yang meremehkan kemampuannya.",
                    "Romance", 4.6f,
                    new String[]{
                            "Elisabeth Zott menjadi simbol pemberdayaan perempuan, memadukan kecerdasan ilmiah dengan kehangatan pribadi.",
                            "Dialog penuh humor dan satire sosial menyulut tawa sekaligus renungan tentang ketidakadilan gender.",
                            "Hubungan Elisa dengan kru siaran berkembang dari skeptisisme menjadi kekaguman yang tulus.",
                            "Akhirnya, penerimaan masyarakat terhadap keunikan Elisa menegaskan pentingnya otentisitas."
                    }, R.drawable.book31));

            books.add(new Book(33, "Demon Copperhead", "Barbara Kingsolver", 2022,
                    "Retelling modern dari kisah David Copperfield, mengikuti Demon, bocah Appalachian yang berjuang melawan kemiskinan, opioid, dan sistem sosial eksploitatif. " +
                            "Gaya monolog yang memikat menyuarakan kemarahan dan harapan, memperlihatkan detail kehidupan pedesaan yang keras, namun dipenuhi solidaritas komunitas. " +
                            "Novel ini memotret tragedi dan ketahanan manusia dengan humor gelap yang menusuk. ",
                    "Drama", 4.5f,
                    new String[]{
                            "Suara naratif Demon kuat dan lugas, menembus lapisan realitas keras dengan lirih tapi berani.",
                            "Kritik sosial disajikan melalui kisah personal, menyoroti krisis opioid dan ketidaksetaraan.",
                            "Karakter sampingan memperkaya cerita, menampilkan sisi lain solidaritas dan persahabatan.",
                            "Akhir novel mengikat konflik dengan pesan harapan yang tak lekang."
                    }, R.drawable.book37));

            books.add(new Book(34, "The Candy House", "Jennifer Egan", 2022,
                    "Sekuel spiritual dari 'A Visit from the Goon Squad', menghubungkan berbagai karakter lewat teknologi memori yang memungkinkan berbagi pengalaman. " +
                            "Setiap bab menjadi potret singkat—dari selebriti hingga orang biasa—mengeksplorasi dampak kehilangan privasi dan pencarian otentisitas di era digital. " +
                            "Egan menenun kisah futuristik yang sekaligus intim dan reflektif. ",
                    "Mystery", 4.2f,
                    new String[]{
                            "Struktur bab pendek memacu ketegangan, setiap karakter memegang kunci cerita besar.",
                            "Kritik soal privasi dan memori digital disampaikan lewat sudut pandang beragam.",
                            "Gaya penulisan Egan menggabungkan kegetiran dan keindahan manusia."
                    }, R.drawable.book33));

            books.add(new Book(35, "Sea of Tranquility", "Emily St. John Mandel", 2022,
                    "Novel lintas waktu ini merajut kisah pandemi abad ke-21 dan kehidupan koloni bulan di masa depan. " +
                            "Karakter utama—seorang musisi, detektif, dan kurator—bersinggungan di sepanjang benang merah misteri kontaminasi global yang menantang realitas. " +
                            "Mandel memadukan realisme emosional dengan spekulasi ilmiah yang menawan. ",
                    "Fantasy", 4.3f,
                    new String[]{
                            "Penggambaran pandemi modern dan koloni bulan terasa autentik dan menggugah emosi.",
                            "Tema realitas alternatif diolah dengan nuansa magis dan filosofis yang mendalam.",
                            "Alur penceritaan transisi waktu disusun rapi, menyuguhkan klimaks emosional."
                    }, R.drawable.book35));

            books.add(new Book(36, "Trust", "Hernan Diaz", 2022,
                    "Empat narasi saling terkait membongkar kisah kekayaan dan kebenaran di era Wall Street 1920-an. " +
                            "Buku ini menantang gagasan obyektivitas sejarah melalui memoar fiktif, surat kabar, dan esai pribadi, menggali siapa yang memegang kuasa menceritakan realita. ",
                    "Biography", 4.1f,
                    new String[]{
                            "Struktur multi-lapisan mengajak pembaca menelaah bias dan manipulasi sejarah.",
                            "Tulisan Diaz elegan, memadu fakta dan fiksi dalam satu alur memikat.",
                            "Sisi gelap kekuasaan dan keadilan dinarasikan dengan canggih."
                    }, R.drawable.book40));

            books.add(new Book(37, "Old Babes in the Wood", "Margot Lee Shetterly", 2023,
                    "Kumpulan memoar kolaboratif oleh wanita kulit hitam di Trenton, NJ, menuturkan pengalaman hidup—mulai diskriminasi, kekuatan keluarga, hingga solidaritas komunitas. " +
                            "Cerita-cerita ini merayakan ketahanan dan kreativitas generasi berbeda, memberikan suara yang sering tenggelam dalam sejarah utama. ",
                    "Drama", 4.4f,
                    new String[]{
                            "Narasi personal menyoroti kekayaan budaya dan sejarah kaum minoritas.",
                            "Kehangatan dan humor natural menambah kedalaman emosional.",
                            "Memoar ini menjadi penghormatan pada kekuatan perempuan melawan tantangan."
                    }, R.drawable.book39));

            books.add(new Book(38, "The Fraud", "Zadie Smith", 2023,
                    "Novel thriller sejarah ini mengangkat kasus Oscar Wilde di London abad ke-19, menyingkap konflik hukum, homofobia, dan pencarian keadilan. " +
                            "Smith membangkitkan era Victoria dengan dramatis melalui riset mendalam, memperlihatkan intrik reputasi dan perlunya keberanian moral. ",
                    "Historical Fiction", 4.0f,
                    new String[]{
                            "Riset historis terasa komprehensif, membangun atmosfer pengadilan klasik.",
                            "Dialog puitis dan tegang memunculkan nuansa drama era Victoria.",
                            "Tema identitas dan kebebasan berekspresi dikaji dengan kritis."
                    }, R.drawable.book36));

            books.add(new Book(39, "Hello Beautiful", "Ann Napolitano", 2023,
                    "Drama keluarga terinspirasi oleh 'The Count of Monte Cristo', mengikuti empat bersaudara yang menghadapi rahasia lama, pengkhianatan, dan penebusan ketika saudara tertua kembali setelah bertahun-tahun hilang. " +
                            "Napolitano mengeksplorasi dinamika cinta dan dendam, membentang emosi dari kebencian hingga kasih yang tulus. ",
                    "Romance", 4.3f,
                    new String[]{
                            "Karakter bersaudara digambarkan dengan kedalaman psikologis yang kuat.",
                            "Alur emosi mengalir deras, membawa pembaca dari keputusasaan hingga harapan." ,
                            "Penutup cerita memadukan kehangatan keluarga dengan rasa kehilangan yang menyentuh."
                    }, R.drawable.book38));

            books.add(new Book(40, "The Heaven & Earth Grocery Store", "James McBride", 2023,
                    "Misteri pembunuhan yang mengoyak komunitas multikultural Pennsylvania pasca-Perang Dunia II, diceritakan oleh seorang veteran yang kembali. " +
                            "McBride merangkai kisah toleransi, sejarah, dan persahabatan dengan humor hangat, menyoroti kekuatan keragaman di tengah konflik. ",
                    "Mystery", 4.5f,
                    new String[]{
                            "Latar pasca-perang memberikan kedalaman historis dan emosional.",
                            "Karakter komunitas direpresentasikan dengan kekayaan budaya dan humor cerdas.",
                            "Plot misteri terstruktur rapi, menyuguhkan klimaks yang mengagetkan."
                    }, R.drawable.book32));
            books.add(new Book(41, "The Paris Apartment", "Lucy Foley", 2023,
                    "Ketika Jess tiba di apartemen saudara laki-lakinya di Paris, yang muncul hanya pintu terkunci dan bisikan tetangga. " +
                            "Rahasia kelam penghuni apartemen mulai terkuak lewat catatan harian dan jejak digital, menciptakan ketegangan tak terduga. " +
                            "Foley piawai membangun atmosfer pengkhianatan dan kecurigaan di lorong-lorong kota romantis yang berbalut misteri.",
                    "Thriller", 4.2f,
                    new String[]{
                            "Suasana Paris yang glamor disandingkan dengan intrik mematikan, menciptakan kontras emosional yang kuat.",
                            "Karakter tetangga satu per satu memilih menutupi kebenaran, menambah lapisan kecurigaan.",
                            "Plot twist di akhir mengejutkan, membuat pembaca menyesali setiap asumsi."
                    }, R.drawable.book41));

            books.add(new Book(42, "Gray After Dark", "Noelle W. Ihli", 2023,
                    "Malam di kota kecil berubah kelam ketika Gray, seorang detektif swasta, menyelidiki hilangnya selebritas lokal. " +
                            "Jejak kejahatan membawa Gray ke klub malam tersembunyi, di mana intrik dan bahaya menanti di balik lampu redup dan musik berdentum. " +
                            "Penuh ketegangan dan plot twist, cerita menelusuri bayang-bayang gelap di balik kemewahan malam.",
                    "Thriller", 4.0f,
                    new String[]{
                            "Suasana klub malam yang intens dipadukan dengan narasi internal Gray yang penuh misteri.",
                            "Plot twist di setiap bab membuat detektif dan pembaca terus diterpa keraguan.",
                            "Ihli menulis dengan ritme cepat, menimbulkan ketegangan tanpa henti hingga halaman terakhir."
                    }, R.drawable.book42));

            books.add(new Book(43, "Children of Time", "Adrian Tchaikovsky", 2015,
                    "Perjalanan manusia meninggalkan Bumi yang sekarat dan menemukan planet baru, di mana evolusi laba-laba cerdas berkembang pesat. " +
                            "Melalui dekade dan milenia, dua peradaban—manusia dan laba-laba—bertemu dalam konflik dan kerjasama. " +
                            "Tchaikovsky menulis epik sains dengan skala kosmik dan detail biologis yang memukau.",
                    "Science Fiction", 4.6f,
                    new String[]{
                            "Riset ilmiah tentang evolusi serangga diolah jadi fiksi memikat.",
                            "Skala waktu yang raksasa memberi perspektif unik tentang peradaban.",
                            "Karakter laba-laba dikembangkan dengan empati dan kecerdasan mendalam."
                    }, R.drawable.book43));

            books.add(new Book(44, "Enceladus", "Brandon Q. Morris", 2021,
                    "Misi penjelajahan Enceladus, bulan bercahaya Saturnus, menghadapi tantangan teknis dan biologis saat tim ilmuwan menyelidiki potensi kehidupan di bawah lapisan es. " +
                            "Mereka harus menembus cekungan air es yang dalam, mengungkap misteri organik di lautan subglasial, sambil bertarung melawan waktu dan sumber daya terbatas.",
                    "Science Fiction", 4.7f,
                    new String[]{
                            "Detail ilmiah yang akurat dan penuh visi tentang eksplorasi antariksa.",
                            "Karakter ilmuwan diperlihatkan dalam dilema moral dan profesional yang mendalam.",
                            "Aksi dan penemuan mengalir intens, membuat pembaca seolah berada di misi nyata."
                    }, R.drawable.book44));

            books.add(new Book(45, "The Ministry for the Future", "Kim Stanley Robinson", 2020,
                    "Organisasi PBB baru dibentuk untuk menangani dampak krisis iklim global. " +
                            "Melalui serangkaian narasi—dari diplomat, ilmuwan, hingga korban bencana—novel ini menggambar masa depan yang mungkin sekaligus menyerukan tindakan segera. " +
                            "Robinson menggabungkan prosa dokumenter dan fiksi spekulatif dengan detail ekonomi, politik, dan ekologi.",
                    "Science Fiction", 4.5f,
                    new String[]{
                            "Pendekatan multi-narator memberikan cakupan global isu iklim.",
                            "Rencana teknis dan kebijakan dikemas realistis, menambah bobot narasi.",
                            "Pesan optimis namun kritis mendorong refleksi pembaca tentang masa depan Bumi."
                    }, R.drawable.book45));
        }
        return books;
    }
    public static Book getBookById(int id) {
        for (Book b : getBooks()) {
            if (b.getId() == id) return b;
        }
        return null;
    }

    public static int generateNewId() {
        int maxId = 0;
        for (Book book : books) {
            if (book.getId() > maxId) {
                maxId = book.getId();
            }
        }
        return maxId + 1;
    }

    public static void addBook(Book book) {
        books.add(book); // Tambah ke list
    }

}
