package com.example.tp4_H071231079;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final int REQ_ADD_POST = 200;
    private RecyclerView recyclerViewPosts, recyclerViewStories;
    private PostAdapter postAdapter;
    private StoryAdapter storyAdapter;
    private List<Post> postList = new ArrayList<>();
    private List<User> userList = new ArrayList<>();
    private int currentUserIndex = 0; // index user yang sedang aktif

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inisialisasi RecyclerViews
        recyclerViewPosts = findViewById(R.id.postrecyclerView);
        recyclerViewStories = findViewById(R.id.recyclerViewStory);
        recyclerViewPosts.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewStories.setLayoutManager(
                new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        );

        // Siapkan data
        createUsersAndPosts();

        // Setup Adapters
        postAdapter = new PostAdapter(this, postList, userList);
        storyAdapter = new StoryAdapter(this, userList);
        recyclerViewPosts.setAdapter(postAdapter);
        recyclerViewStories.setAdapter(storyAdapter);

        // Bottom nav: item_post -> buka AddPostActivity
        ImageView navPost = findViewById(R.id.item_post);
        navPost.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddPostActivity.class);

            // Ambil username dari user yang sedang aktif
            User currentUser = userList.get(currentUserIndex);
            intent.putExtra("username", currentUser.getUsername()); // Kirim username

            startActivityForResult(intent, REQ_ADD_POST);
        });

        ImageView btnProfile = findViewById(R.id.imageProfile2);
        btnProfile.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ProfileAccountActivity.class);

            // Ambil user yang sedang aktif
            User currentUser = userList.get(currentUserIndex);

            // Kirim seluruh objek User
            intent.putExtra("USER_DATA", currentUser);
            intent.putExtra("USER_INDEX", currentUserIndex);

            // Kirim posts
            intent.putParcelableArrayListExtra("posts", new ArrayList<>(currentUser.getPosts()));

            // Kirim highlights
            intent.putParcelableArrayListExtra("highlights", new ArrayList<>(currentUser.getHighlights()));

            // Kirim count: posts, followers, following
            intent.putExtra("postsCount", currentUser.getPosts() != null ? currentUser.getPosts().size() : 0);
            intent.putExtra("followersCount", currentUser.getFollowersCount());
            intent.putExtra("followingCount", currentUser.getFollowingCount());

            // Start activity
            startActivityForResult(intent, 300);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_ADD_POST && resultCode == RESULT_OK && data != null) {
            Uri imageUri = Uri.parse(data.getStringExtra("imageUri"));
            String caption = data.getStringExtra("caption");
            String username = data.getStringExtra("username"); // Ambil username

            // Cari user berdasarkan username
            User currentUser = null;
            for (User user : userList) {
                if (user.getUsername().equals(username)) {
                    currentUser = user;
                    break;
                }
            }

            if (currentUser != null) {
                // Buat post baru
                Post newPost = new Post(currentUser, imageUri, caption);

                // Tambahkan ke user dan feed keseluruhan
                currentUser.addPost(newPost);
                postList.add(0, newPost);

                // Update UI
                postAdapter.notifyItemInserted(0);
                recyclerViewPosts.scrollToPosition(0);
            }
        }
    }

    private void createUsersAndPosts() {
        // List untuk collect post
        userList = new ArrayList<>();
        postList = new ArrayList<>();

        // User 1: kelompok8_
        List<Post> user1Posts = new ArrayList<>();
        User user1 = new User(R.drawable.profile1, "kelompok8_",
                "Kelompok Imut",
                "kalakasming",
                120,
                200,
                "Followed by elira.sun, misty.belle, ivy.wave and 3 others",
                new int[]{R.drawable.post1, R.drawable.k81, R.drawable.k82, R.drawable.k83, R.drawable.k84, R.drawable.k85, R.drawable.k86},
                new String[]{"Besties", "Ride", "Sunset", "Trip", "Adventure", "Souvenir", "Lens"},
                new Story(R.drawable.post1),
                new ArrayList<>());

        user1Posts.add(new Post(user1, R.drawable.post1, "Weekend getaway with the squad 🌴 #Besties #TravelGoals"));
        user1Posts.add(new Post(user1, R.drawable.k81, "Exploring new places with my ride or dies ✈️"));
        user1Posts.add(new Post(user1, R.drawable.k82, "Sunsets are better when shared 🌅"));
        user1Posts.add(new Post(user1, R.drawable.k83, "Making memories one trip at a time 📸"));
        user1Posts.add(new Post(user1, R.drawable.k84, "Adventure is out there! 🗺️"));
        user1Posts.add(new Post(user1, R.drawable.k85, "Souvenirs from our latest adventure 🎒"));
        user1Posts.add(new Post(user1, R.drawable.k86, "The world through our lens 🌍"));

        // Update posts user1
        user1.setPosts(user1Posts);

        userList.add(user1);

        // User 2: harmeliayra_
        List<Post> user2Posts = new ArrayList<>();

        User user2 = new User(
                R.drawable.profile2,
                "harmeliayra_",
                "Harmeli Ayra",
                "Dream big, stay positive",
                500,
                150,
                "Followed by jasmineinyh, vaerizz_, lumi.ray and 2 others",
                new int[]{R.drawable.post2, R.drawable.me2, R.drawable.me3, R.drawable.me4, R.drawable.me5, R.drawable.me3, R.drawable.me2},
                new String[]{"Life", "Pets", "Joy", "Self-care", "Growth", "Pawsitive", "Sunsets"},
                new Story(R.drawable.post2),
                new ArrayList<>());

        user2Posts.add(new Post(user2, R.drawable.post2, "Morning coffee and good vibes ☕ #LifeIsBeautiful"));
        user2Posts.add(new Post(user2, R.drawable.me2, "My new furry friend says hi! 🐾 #PetLove"));
        user2Posts.add(new Post(user2, R.drawable.me3, "Find joy in little things 🌼"));
        user2Posts.add(new Post(user2, R.drawable.me4, "Self-care Sunday with my baby 🐶"));
        user2Posts.add(new Post(user2, R.drawable.me5, "Growth looks good on me 🌱"));
        user2Posts.add(new Post(user2, R.drawable.me3, "Pawsitive vibes only 🐕"));
        user2Posts.add(new Post(user2, R.drawable.me2, "Chasing sunsets and dreams 🌇"));

        // Update posts user2
        user2.setPosts(user2Posts);

        userList.add(user2);

        // User 3: kucingimuut
        List<Post> user3Posts = new ArrayList<>();
        User user3 = new User(
                R.drawable.profile3,
                "kucingimuut",
                "Kucing Imut",
                "Miaw miaw bestie",
                1000,
                450,
                "Followed by catniverse, meowtime, whisker.milo and 1 other",
                new int[]{R.drawable.cat1, R.drawable.cat2, R.drawable.cat3, R.drawable.cat4, R.drawable.cat5, R.drawable.cat6, R.drawable.cat7},
                new String[]{"Cute", "Meow Life", "Treats", "Box", "Kingdom", "Blep", "Adorable"},
                new Story(R.drawable.post3),
                new ArrayList<>());
        user3Posts.add(new Post(user3, R.drawable.cat1, "Nap time is the best time 😴 #CatLife"));
        user3Posts.add(new Post(user3, R.drawable.cat2, "Who needs personal space anyway? 😼"));
        user3Posts.add(new Post(user3, R.drawable.cat3, "Treats please? 🥺 #Meow"));
        user3Posts.add(new Post(user3, R.drawable.cat4, "Professional box inspector 📦"));
        user3Posts.add(new Post(user3, R.drawable.cat5, "This is my kingdom now 👑"));
        user3Posts.add(new Post(user3, R.drawable.cat6, "Blep 👅 #CatsOfInstagram"));
        user3Posts.add(new Post(user3, R.drawable.cat7, "Caught in 4K 📸 being adorable"));

        // Update posts user3
        user3.setPosts(user3Posts);

        userList.add(user3);

        // User 4: lostinverses
        List<Post> user4Posts = new ArrayList<>();

        User user4 = new User(
                R.drawable.profile4,
                "lostinverses",
                "Poetry Ghost",
                "Writing verses no one reads",
                20000,
                800,
                "Followed by dusk.writer, lonely.quotes, shadow_poets, and 2 others",
                new int[]{R.drawable.post4, R.drawable.post4},
                new String[]{"Poetry", "Sad"},
                new Story(R.drawable.post4),
                new ArrayList<>());
        user4Posts.add(new Post(user4, R.drawable.post4, "Ink stains and broken chains ✒️ #PoetryInMotion"));
        user4Posts.add(new Post(user4, R.drawable.post4, "Words left unspoken 📜"));
        user4Posts.add(new Post(user4, R.drawable.post4, "Melancholy looks good on me 🌫️"));
        user4Posts.add(new Post(user4, R.drawable.post4, "Between the lines of sanity 📖"));
        user4Posts.add(new Post(user4, R.drawable.post4, "Souls don't rust, they write 🖋️"));
        user4Posts.add(new Post(user4, R.drawable.post4, "Echoes of yesterday's verses 📓"));
        user4Posts.add(new Post(user4, R.drawable.post4, "Poetry is my therapy 🌌"));

        // Update posts user4
        user4.setPosts(user4Posts);

        userList.add(user4);

        // User 5: green.planet
        List<Post> user5Posts = new ArrayList<>();
        User user5 = new User(
                R.drawable.profile5,
                "green.planet",
                "Green Planet",
                "Energize the Earth 🌱⚡",
                1280,
                412,
                "Followed by eco.future, solarwise.id, climate.change.now and 3 others",
                new int[]{R.drawable.ge1, R.drawable.ge2, R.drawable.ge3, R.drawable.ge4, R.drawable.ge5, R.drawable.ge6, R.drawable.ge7},
                new String[]{"Solar Power", "Wind Energy", "Sustainability", "Go Green", "Eco Tips", "Carbon Neutral", "Green Projects"},
                new Story(R.drawable.ge1),
                new ArrayList<>());
        user5Posts.add(new Post(user5, R.drawable.ge1, "Harnessing the power of nature 🌞 #SolarEnergy"));
        user5Posts.add(new Post(user5, R.drawable.ge2, "Wind turbines dancing with the sky 🌪️"));
        user5Posts.add(new Post(user5, R.drawable.ge3, "Small steps for greener future 🌱"));
        user5Posts.add(new Post(user5, R.drawable.ge4, "Urban jungle meets real jungle 🌿"));
        user5Posts.add(new Post(user5, R.drawable.ge5, "Zero waste lifestyle goals ♻️"));
        user5Posts.add(new Post(user5, R.drawable.ge6, "Carbon neutral by 2030 🎯"));
        user5Posts.add(new Post(user5, R.drawable.ge7, "Community garden project update 🌻"));

        // Update posts user5
        user5.setPosts(user5Posts);

        userList.add(user5);

        // User 6: pastelglow
        List<Post> user6Posts = new ArrayList<>();

        User user6 = new User(
                R.drawable.profile6,
                "pastelglow",
                "Glow by Lia",
                "Soft glam & subtle slay ✨",
                9200,
                420,
                "Followed by blendbabe.id, peachy.glow, lipstain.lust and 2 others",
                new int[]{R.drawable.makeup1, R.drawable.makeup2, R.drawable.makeup3, R.drawable.makeup4, R.drawable.makeup5, R.drawable.makeup6, R.drawable.makeup7},
                new String[]{"Blush", "Lipgloss", "No-Makeup Look", "Pastel Vibes", "Skincare", "Everyday Look", "Beauty Flatlay"},
                new Story(R.drawable.makeup1),
                new ArrayList<>());
        user6Posts.add(new Post(user6, R.drawable.makeup1, "Peachy keen blush routine 🍑 #MakeupLover"));
        user6Posts.add(new Post(user6, R.drawable.makeup2, "Glossy lips don't lie 💋"));
        user6Posts.add(new Post(user6, R.drawable.makeup3, "No makeup makeup look achieved! ✨"));
        user6Posts.add(new Post(user6, R.drawable.makeup4, "Pastel dreams coming true 🌈"));
        user6Posts.add(new Post(user6, R.drawable.makeup5, "Skincare is self-care 💆♀️"));
        user6Posts.add(new Post(user6, R.drawable.makeup6, "Easy 5-minute everyday look ⏱️"));
        user6Posts.add(new Post(user6, R.drawable.makeup7, "New products alert! 🛍️ BeautyFlatlay"));

        // Update posts user6
        user6.setPosts(user6Posts);

        userList.add(user6);

        // User 7: ghiblivibes
        List<Post> user7Posts = new ArrayList<>();
        User user7 = new User(
                R.drawable.profile7,
                "ghiblivibes",
                "Studio Ghibli Fan",
                "Floating with Totoro since '88 🍃",
                8700,
                340,
                "Followed by spirited.chi, howl.jen, sootball.collector and 4 others",
                new int[]{R.drawable.ghibli1, R.drawable.ghibli2, R.drawable.ghibli3, R.drawable.ghibli4, R.drawable.ghibli5, R.drawable.ghibli6, R.drawable.ghibli7},
                new String[]{"Totoro", "Howl", "Spirited Away", "Ghibli Art", "Nature", "Anime Mood", "Studio Vibes"},
                new Story(R.drawable.ghibli1),
                new ArrayList<>());
        user7Posts.add(new Post(user7, R.drawable.ghibli1, "Totoro says good morning! 🌳 #GhibliMagic"));
        user7Posts.add(new Post(user7, R.drawable.ghibli2, "Howl's moving castle vibes 🏰"));
        user7Posts.add(new Post(user7, R.drawable.ghibli3, "Spirited away to another world 🐉"));
        user7Posts.add(new Post(user7, R.drawable.ghibli4, "Studio Ghibli art appreciation 🎨"));
        user7Posts.add(new Post(user7, R.drawable.ghibli5, "Nature meets animation 🌿"));
        user7Posts.add(new Post(user7, R.drawable.ghibli6, "Anime mood: Nostalgic 🍂"));
        user7Posts.add(new Post(user7, R.drawable.ghibli7, "Studio vibes ✨ frame by frame magic"));

        // Update posts user7
        user7.setPosts(user7Posts);

        userList.add(user7);

        // User 8: the1975.lyric
        List<Post> user8Posts = new ArrayList<>();

        User user8 = new User(
                R.drawable.profile8,
                "the1975.lyric",
                "1975 Archive",
                "Matty Healy apologist 🎤",
                15600,
                712,
                "Followed by greta.rose, neon.hearts, dirtyhit.fans and 5 others",
                new int[]{R.drawable.the19751, R.drawable.the19752, R.drawable.the19753, R.drawable.the19754, R.drawable.the19755, R.drawable.the19756, R.drawable.the19757},
                new String[]{"Lyrics", "Live", "Album Art", "Band", "Indie", "Aesthetic", "1975 Mood"},
                new Story(R.drawable.the19751),
                new ArrayList<>());
        user8Posts.add(new Post(user8, R.drawable.the19751, "I'm in love with the shape of you 🎶 #The1975Lyrics"));
        user8Posts.add(new Post(user8, R.drawable.the19752, "Live concert memories 🎤"));
        user8Posts.add(new Post(user8, R.drawable.the19753, "Album art appreciation post 🖼️"));
        user8Posts.add(new Post(user8, R.drawable.the19754, "Band practice vibes 🎸"));
        user8Posts.add(new Post(user8, R.drawable.the19755, "Indie music saves souls 🖤"));
        user8Posts.add(new Post(user8, R.drawable.the19756, "Aesthetic midnight thoughts 🌃"));
        user8Posts.add(new Post(user8, R.drawable.the19757, "1975 mood: Forever iconic ✨"));

        // Update post user8
        user8.setPosts(user8Posts);

        userList.add(user8);

        // User 9: chasersunited
        List<Post> user9Posts = new ArrayList<>();

        User user9 = new User(
                R.drawable.profile9,
                "chasersunited",
                "Chase Atlantic Zone",
                "My toxic trait is looping Swim 🔁",
                19800,
                680,
                "Followed by 23toxicdreams, chasewave, midnighttrax and 3 others",
                new int[]{R.drawable.chase1, R.drawable.chase2, R.drawable.chase3, R.drawable.chase4, R.drawable.chase5, R.drawable.chase6, R.drawable.chase7},
                new String[]{"Tour", "Tracks", "Quotes", "Swim", "Darkwave", "Band Life", "Late Night"},
                new Story(R.drawable.chase1),
                new ArrayList<>());
        user9Posts.add(new Post(user9, R.drawable.chase1, "Tour dates announced! 🎫 #ChaseAtlantic"));
        user9Posts.add(new Post(user9, R.drawable.chase2, "Lyrics that hit different 🎧"));
        user9Posts.add(new Post(user9, R.drawable.chase3, "Quote of the day from Swim 🌊"));
        user9Posts.add(new Post(user9, R.drawable.chase4, "Darkwave nights 🌑"));
        user9Posts.add(new Post(user9, R.drawable.chase5, "Behind the scenes: Band life 📸"));
        user9Posts.add(new Post(user9, R.drawable.chase6, "Midnight studio sessions 🌙"));
        user9Posts.add(new Post(user9, R.drawable.chase7, "Looping this track all night 🔁"));

        //Update post user9
        user9.setPosts(user9Posts);

        userList.add(user9);

        // User 10: hidupbubur
        List<Post> user10Posts = new ArrayList<>();

        User user10 = new User(
                R.drawable.profile10,
                "hidupbubur",
                "Bubur Ayam Lovers",
                "Diaduk atau tidak? Kita tetap saudara 🥣",
                1250,
                203,
                "Followed by kulinerku.id, makanmulu, foodfest_jkt and 1 other",
                new int[]{R.drawable.bubur1, R.drawable.bubur2, R.drawable.bubur3, R.drawable.bubur4, R.drawable.bubur5, R.drawable.bubur6, R.drawable.bubur7},
                new String[]{"Street Food", "Comfort Food", "Bubur Nusantara", "Kuliner Pagi", "Topping Wars", "Foodie", "Sambal Challenge"},
                new Story(R.drawable.bubur1),
                new ArrayList<>());
        user10Posts.add(new Post(user10, R.drawable.bubur1, "Bubur ayam special dengan topping super! 🍗 #KulinerMalang"));
        user10Posts.add(new Post(user10, R.drawable.bubur2, "Sarapan sehat ala Nusantara 🥣"));
        user10Posts.add(new Post(user10, R.drawable.bubur3, "Bubur hangat untuk hari hujan ☔"));
        user10Posts.add(new Post(user10, R.drawable.bubur4, "Topping wars: Kacang vs Bawang? 😂"));
        user10Posts.add(new Post(user10, R.drawable.bubur5, "Resep warisan nenek 👵❤️"));
        user10Posts.add(new Post(user10, R.drawable.bubur6, "Food photography practice 📸"));
        user10Posts.add(new Post(user10, R.drawable.bubur7, "Sambal level: Naga 🔥🌶️"));

        //Update post user10
        user10.setPosts(user10Posts);

        userList.add(user10);

        for (User user : userList) {
            List<Post> posts = user.getPosts();
            if (!posts.isEmpty()) {
                postList.add(posts.get(posts.size() - 1)); // Ambil post terakhir
            }
        }
    }
}
