package com.arifian.udacity.booklisting;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.arifian.udacity.booklisting.adapters.BookRecyclerAdapter;
import com.arifian.udacity.booklisting.entities.Book;
import com.arifian.udacity.booklisting.utils.JSONUtils;
import com.arifian.udacity.booklisting.view.SpacesItemDecoration;

import java.util.List;

public class SearchResultActivity extends AppCompatActivity {
    public static final String KEY_QUERY = "query";
    String query;
    List<Book> books;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        query = getIntent().getStringExtra(KEY_QUERY);
        Log.e("QUERY", query);

        String jsonStr = "{\n" +
                " \"kind\": \"books#volumes\",\n" +
                " \"totalItems\": 518,\n" +
                " \"items\": [\n" +
                "  {\n" +
                "   \"kind\": \"books#volume\",\n" +
                "   \"id\": \"MoXpe6H2B5gC\",\n" +
                "   \"etag\": \"Xol35h3eoEY\",\n" +
                "   \"selfLink\": \"https://www.googleapis.com/books/v1/volumes/MoXpe6H2B5gC\",\n" +
                "   \"volumeInfo\": {\n" +
                "    \"title\": \"Android in The Attic\",\n" +
                "    \"authors\": [\n" +
                "     \"Nicholas Allan\"\n" +
                "    ],\n" +
                "    \"publisher\": \"Hachette UK\",\n" +
                "    \"publishedDate\": \"2013-01-03\",\n" +
                "    \"description\": \"Aunt Edna has created a no-nonsense nanny android to make sure Billy and Alfie don't have any fun. But then Alfie discovers how to override Auntie Anne-Droid's programming and nothing can stop them eating all the Cheeki Choko Cherry Cakes they like ... until the real aunt Edna is kidnapped!\",\n" +
                "    \"industryIdentifiers\": [\n" +
                "     {\n" +
                "      \"type\": \"ISBN_13\",\n" +
                "      \"identifier\": \"9781444905465\"\n" +
                "     },\n" +
                "     {\n" +
                "      \"type\": \"ISBN_10\",\n" +
                "      \"identifier\": \"1444905465\"\n" +
                "     }\n" +
                "    ],\n" +
                "    \"readingModes\": {\n" +
                "     \"text\": true,\n" +
                "     \"image\": false\n" +
                "    },\n" +
                "    \"pageCount\": 192,\n" +
                "    \"printType\": \"BOOK\",\n" +
                "    \"categories\": [\n" +
                "     \"Juvenile Fiction\"\n" +
                "    ],\n" +
                "    \"maturityRating\": \"NOT_MATURE\",\n" +
                "    \"allowAnonLogging\": false,\n" +
                "    \"contentVersion\": \"1.2.2.0.preview.2\",\n" +
                "    \"panelizationSummary\": {\n" +
                "     \"containsEpubBubbles\": false,\n" +
                "     \"containsImageBubbles\": false\n" +
                "    },\n" +
                "    \"imageLinks\": {\n" +
                "     \"smallThumbnail\": \"http://books.google.com/books/content?id=MoXpe6H2B5gC&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api\",\n" +
                "     \"thumbnail\": \"http://books.google.com/books/content?id=MoXpe6H2B5gC&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api\"\n" +
                "    },\n" +
                "    \"language\": \"en\",\n" +
                "    \"previewLink\": \"http://books.google.co.id/books?id=MoXpe6H2B5gC&printsec=frontcover&dq=android&hl=&cd=1&source=gbs_api\",\n" +
                "    \"infoLink\": \"https://play.google.com/store/books/details?id=MoXpe6H2B5gC&source=gbs_api\",\n" +
                "    \"canonicalVolumeLink\": \"https://market.android.com/details?id=book-MoXpe6H2B5gC\"\n" +
                "   },\n" +
                "   \"saleInfo\": {\n" +
                "    \"country\": \"ID\",\n" +
                "    \"saleability\": \"FOR_SALE\",\n" +
                "    \"isEbook\": true,\n" +
                "    \"listPrice\": {\n" +
                "     \"amount\": 61637.0,\n" +
                "     \"currencyCode\": \"IDR\"\n" +
                "    },\n" +
                "    \"retailPrice\": {\n" +
                "     \"amount\": 46228.0,\n" +
                "     \"currencyCode\": \"IDR\"\n" +
                "    },\n" +
                "    \"buyLink\": \"https://play.google.com/store/books/details?id=MoXpe6H2B5gC&rdid=book-MoXpe6H2B5gC&rdot=1&source=gbs_api\",\n" +
                "    \"offers\": [\n" +
                "     {\n" +
                "      \"finskyOfferType\": 1,\n" +
                "      \"listPrice\": {\n" +
                "       \"amountInMicros\": 6.1637E10,\n" +
                "       \"currencyCode\": \"IDR\"\n" +
                "      },\n" +
                "      \"retailPrice\": {\n" +
                "       \"amountInMicros\": 4.6228E10,\n" +
                "       \"currencyCode\": \"IDR\"\n" +
                "      }\n" +
                "     }\n" +
                "    ]\n" +
                "   },\n" +
                "   \"accessInfo\": {\n" +
                "    \"country\": \"ID\",\n" +
                "    \"viewability\": \"PARTIAL\",\n" +
                "    \"embeddable\": true,\n" +
                "    \"publicDomain\": false,\n" +
                "    \"textToSpeechPermission\": \"ALLOWED_FOR_ACCESSIBILITY\",\n" +
                "    \"epub\": {\n" +
                "     \"isAvailable\": true,\n" +
                "     \"acsTokenLink\": \"http://books.google.co.id/books/download/Android_in_The_Attic-sample-epub.acsm?id=MoXpe6H2B5gC&format=epub&output=acs4_fulfillment_token&dl_type=sample&source=gbs_api\"\n" +
                "    },\n" +
                "    \"pdf\": {\n" +
                "     \"isAvailable\": false\n" +
                "    },\n" +
                "    \"webReaderLink\": \"http://books.google.co.id/books/reader?id=MoXpe6H2B5gC&hl=&printsec=frontcover&output=reader&source=gbs_api\",\n" +
                "    \"accessViewStatus\": \"SAMPLE\",\n" +
                "    \"quoteSharingAllowed\": false\n" +
                "   },\n" +
                "   \"searchInfo\": {\n" +
                "    \"textSnippet\": \"Aunt Edna has created a no-nonsense nanny android to make sure Billy and Alfie don&#39;t have any fun.\"\n" +
                "   }\n" +
                "  },\n" +
                "  {\n" +
                "   \"kind\": \"books#volume\",\n" +
                "   \"id\": \"H8tNBKmPO5UC\",\n" +
                "   \"etag\": \"Qk+1WZdhl7E\",\n" +
                "   \"selfLink\": \"https://www.googleapis.com/books/v1/volumes/H8tNBKmPO5UC\",\n" +
                "   \"volumeInfo\": {\n" +
                "    \"title\": \"Android Fully Loaded\",\n" +
                "    \"authors\": [\n" +
                "     \"Rob Huddleston\"\n" +
                "    ],\n" +
                "    \"publisher\": \"John Wiley & Sons\",\n" +
                "    \"publishedDate\": \"2012-08-03\",\n" +
                "    \"industryIdentifiers\": [\n" +
                "     {\n" +
                "      \"type\": \"ISBN_13\",\n" +
                "      \"identifier\": \"9781118234914\"\n" +
                "     },\n" +
                "     {\n" +
                "      \"type\": \"ISBN_10\",\n" +
                "      \"identifier\": \"111823491X\"\n" +
                "     }\n" +
                "    ],\n" +
                "    \"readingModes\": {\n" +
                "     \"text\": true,\n" +
                "     \"image\": true\n" +
                "    },\n" +
                "    \"pageCount\": 256,\n" +
                "    \"printType\": \"BOOK\",\n" +
                "    \"categories\": [\n" +
                "     \"Computers\"\n" +
                "    ],\n" +
                "    \"maturityRating\": \"NOT_MATURE\",\n" +
                "    \"allowAnonLogging\": true,\n" +
                "    \"contentVersion\": \"2.2.2.0.preview.3\",\n" +
                "    \"imageLinks\": {\n" +
                "     \"smallThumbnail\": \"http://books.google.com/books/content?id=H8tNBKmPO5UC&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api\",\n" +
                "     \"thumbnail\": \"http://books.google.com/books/content?id=H8tNBKmPO5UC&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api\"\n" +
                "    },\n" +
                "    \"language\": \"en\",\n" +
                "    \"previewLink\": \"http://books.google.co.id/books?id=H8tNBKmPO5UC&printsec=frontcover&dq=android&hl=&cd=2&source=gbs_api\",\n" +
                "    \"infoLink\": \"http://books.google.co.id/books?id=H8tNBKmPO5UC&dq=android&hl=&source=gbs_api\",\n" +
                "    \"canonicalVolumeLink\": \"https://books.google.com/books/about/Android_Fully_Loaded.html?hl=&id=H8tNBKmPO5UC\"\n" +
                "   },\n" +
                "   \"saleInfo\": {\n" +
                "    \"country\": \"ID\",\n" +
                "    \"saleability\": \"NOT_FOR_SALE\",\n" +
                "    \"isEbook\": false\n" +
                "   },\n" +
                "   \"accessInfo\": {\n" +
                "    \"country\": \"ID\",\n" +
                "    \"viewability\": \"PARTIAL\",\n" +
                "    \"embeddable\": true,\n" +
                "    \"publicDomain\": false,\n" +
                "    \"textToSpeechPermission\": \"ALLOWED\",\n" +
                "    \"epub\": {\n" +
                "     \"isAvailable\": true,\n" +
                "     \"acsTokenLink\": \"http://books.google.co.id/books/download/Android_Fully_Loaded-sample-epub.acsm?id=H8tNBKmPO5UC&format=epub&output=acs4_fulfillment_token&dl_type=sample&source=gbs_api\"\n" +
                "    },\n" +
                "    \"pdf\": {\n" +
                "     \"isAvailable\": true,\n" +
                "     \"acsTokenLink\": \"http://books.google.co.id/books/download/Android_Fully_Loaded-sample-pdf.acsm?id=H8tNBKmPO5UC&format=pdf&output=acs4_fulfillment_token&dl_type=sample&source=gbs_api\"\n" +
                "    },\n" +
                "    \"webReaderLink\": \"http://books.google.co.id/books/reader?id=H8tNBKmPO5UC&hl=&printsec=frontcover&output=reader&source=gbs_api\",\n" +
                "    \"accessViewStatus\": \"SAMPLE\",\n" +
                "    \"quoteSharingAllowed\": false\n" +
                "   },\n" +
                "   \"searchInfo\": {\n" +
                "    \"textSnippet\": \"Get Android Fully Loaded, Second Edition and don&#39;t miss a thing! Take your love affair with Android to a new level Since the first edition of this book, new phones and new apps have been popping up like monsters at the game-master&#39;s level.\"\n" +
                "   }\n" +
                "  },\n" +
                "  {\n" +
                "   \"kind\": \"books#volume\",\n" +
                "   \"id\": \"gAcjBgAAQBAJ\",\n" +
                "   \"etag\": \"oTzgkD9iAcI\",\n" +
                "   \"selfLink\": \"https://www.googleapis.com/books/v1/volumes/gAcjBgAAQBAJ\",\n" +
                "   \"volumeInfo\": {\n" +
                "    \"title\": \"Membuat Aplikasi Android SMS Gateway\",\n" +
                "    \"subtitle\": \"Cara Cepat dan Mudah Membuat SMS Gateway Server dengan Android\",\n" +
                "    \"authors\": [\n" +
                "     \"Eko Kurniawan Khannedy\"\n" +
                "    ],\n" +
                "    \"publisher\": \"Eko Kurniawan Khannedy\",\n" +
                "    \"description\": \"Pada buku ini saya membahas cara membuat aplikasi android yang dapat kita gunakan sebagai SMS Gateway. Dengan aplikasi yang dibuat dalam buku ini, kita tidak perlu lagi menggunakan aplikasi pihak ke-3 seperti GAMMU atau bahkan tidak perlu menggunakan AT-COMMAND. Dan yang paling penting, aplikasi Android SMS Gateway yang dibuat dalam buku ini bisa digunakan oleh teknologi / pemrograman apapun, mau itu Java, PHP, Ruby, NodeJS dan semuanya. Integrasinya pun GAK PAKE RIBET, gak usah install driver dan gak usah colok kabel serial segala\",\n" +
                "    \"readingModes\": {\n" +
                "     \"text\": false,\n" +
                "     \"image\": true\n" +
                "    },\n" +
                "    \"printType\": \"BOOK\",\n" +
                "    \"maturityRating\": \"NOT_MATURE\",\n" +
                "    \"allowAnonLogging\": true,\n" +
                "    \"contentVersion\": \"preview-1.0.0\",\n" +
                "    \"panelizationSummary\": {\n" +
                "     \"containsEpubBubbles\": false,\n" +
                "     \"containsImageBubbles\": false\n" +
                "    },\n" +
                "    \"imageLinks\": {\n" +
                "     \"smallThumbnail\": \"http://books.google.com/books/content?id=gAcjBgAAQBAJ&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api\",\n" +
                "     \"thumbnail\": \"http://books.google.com/books/content?id=gAcjBgAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api\"\n" +
                "    },\n" +
                "    \"language\": \"id\",\n" +
                "    \"previewLink\": \"http://books.google.co.id/books?id=gAcjBgAAQBAJ&printsec=frontcover&dq=android&hl=&cd=3&source=gbs_api\",\n" +
                "    \"infoLink\": \"https://play.google.com/store/books/details?id=gAcjBgAAQBAJ&source=gbs_api\",\n" +
                "    \"canonicalVolumeLink\": \"https://market.android.com/details?id=book-gAcjBgAAQBAJ\"\n" +
                "   },\n" +
                "   \"saleInfo\": {\n" +
                "    \"country\": \"ID\",\n" +
                "    \"saleability\": \"FOR_SALE\",\n" +
                "    \"isEbook\": true,\n" +
                "    \"listPrice\": {\n" +
                "     \"amount\": 200000.0,\n" +
                "     \"currencyCode\": \"IDR\"\n" +
                "    },\n" +
                "    \"retailPrice\": {\n" +
                "     \"amount\": 136000.0,\n" +
                "     \"currencyCode\": \"IDR\"\n" +
                "    },\n" +
                "    \"buyLink\": \"https://play.google.com/store/books/details?id=gAcjBgAAQBAJ&rdid=book-gAcjBgAAQBAJ&rdot=1&source=gbs_api\",\n" +
                "    \"offers\": [\n" +
                "     {\n" +
                "      \"finskyOfferType\": 1,\n" +
                "      \"listPrice\": {\n" +
                "       \"amountInMicros\": 2.0E11,\n" +
                "       \"currencyCode\": \"IDR\"\n" +
                "      },\n" +
                "      \"retailPrice\": {\n" +
                "       \"amountInMicros\": 1.36E11,\n" +
                "       \"currencyCode\": \"IDR\"\n" +
                "      }\n" +
                "     }\n" +
                "    ]\n" +
                "   },\n" +
                "   \"accessInfo\": {\n" +
                "    \"country\": \"ID\",\n" +
                "    \"viewability\": \"PARTIAL\",\n" +
                "    \"embeddable\": true,\n" +
                "    \"publicDomain\": false,\n" +
                "    \"textToSpeechPermission\": \"ALLOWED\",\n" +
                "    \"epub\": {\n" +
                "     \"isAvailable\": false\n" +
                "    },\n" +
                "    \"pdf\": {\n" +
                "     \"isAvailable\": true,\n" +
                "     \"acsTokenLink\": \"http://books.google.co.id/books/download/Membuat_Aplikasi_Android_SMS_Gateway-sample-pdf.acsm?id=gAcjBgAAQBAJ&format=pdf&output=acs4_fulfillment_token&dl_type=sample&source=gbs_api\"\n" +
                "    },\n" +
                "    \"webReaderLink\": \"http://books.google.co.id/books/reader?id=gAcjBgAAQBAJ&hl=&printsec=frontcover&output=reader&source=gbs_api\",\n" +
                "    \"accessViewStatus\": \"SAMPLE\",\n" +
                "    \"quoteSharingAllowed\": false\n" +
                "   },\n" +
                "   \"searchInfo\": {\n" +
                "    \"textSnippet\": \"Pada buku ini saya membahas cara membuat aplikasi android yang dapat kita gunakan sebagai SMS Gateway.\"\n" +
                "   }\n" +
                "  },\n" +
                "  {\n" +
                "   \"kind\": \"books#volume\",\n" +
                "   \"id\": \"lb3KwEeSkiUC\",\n" +
                "   \"etag\": \"ayEjWiA4dq4\",\n" +
                "   \"selfLink\": \"https://www.googleapis.com/books/v1/volumes/lb3KwEeSkiUC\",\n" +
                "   \"volumeInfo\": {\n" +
                "    \"title\": \"Ponsel Android\",\n" +
                "    \"authors\": [\n" +
                "     \"Jubilee enterprise\"\n" +
                "    ],\n" +
                "    \"publisher\": \"Elex Media Komputindo\",\n" +
                "    \"industryIdentifiers\": [\n" +
                "     {\n" +
                "      \"type\": \"ISBN_10\",\n" +
                "      \"identifier\": \"9792774068\"\n" +
                "     },\n" +
                "     {\n" +
                "      \"type\": \"ISBN_13\",\n" +
                "      \"identifier\": \"9789792774061\"\n" +
                "     }\n" +
                "    ],\n" +
                "    \"readingModes\": {\n" +
                "     \"text\": false,\n" +
                "     \"image\": true\n" +
                "    },\n" +
                "    \"printType\": \"BOOK\",\n" +
                "    \"maturityRating\": \"NOT_MATURE\",\n" +
                "    \"allowAnonLogging\": false,\n" +
                "    \"contentVersion\": \"0.0.1.0.preview.1\",\n" +
                "    \"imageLinks\": {\n" +
                "     \"smallThumbnail\": \"http://books.google.com/books/content?id=lb3KwEeSkiUC&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api\",\n" +
                "     \"thumbnail\": \"http://books.google.com/books/content?id=lb3KwEeSkiUC&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api\"\n" +
                "    },\n" +
                "    \"language\": \"id\",\n" +
                "    \"previewLink\": \"http://books.google.co.id/books?id=lb3KwEeSkiUC&pg=PA1&dq=android&hl=&cd=4&source=gbs_api\",\n" +
                "    \"infoLink\": \"http://books.google.co.id/books?id=lb3KwEeSkiUC&dq=android&hl=&source=gbs_api\",\n" +
                "    \"canonicalVolumeLink\": \"https://books.google.com/books/about/Ponsel_Android.html?hl=&id=lb3KwEeSkiUC\"\n" +
                "   },\n" +
                "   \"saleInfo\": {\n" +
                "    \"country\": \"ID\",\n" +
                "    \"saleability\": \"NOT_FOR_SALE\",\n" +
                "    \"isEbook\": false\n" +
                "   },\n" +
                "   \"accessInfo\": {\n" +
                "    \"country\": \"ID\",\n" +
                "    \"viewability\": \"PARTIAL\",\n" +
                "    \"embeddable\": true,\n" +
                "    \"publicDomain\": false,\n" +
                "    \"textToSpeechPermission\": \"ALLOWED\",\n" +
                "    \"epub\": {\n" +
                "     \"isAvailable\": false\n" +
                "    },\n" +
                "    \"pdf\": {\n" +
                "     \"isAvailable\": false\n" +
                "    },\n" +
                "    \"webReaderLink\": \"http://books.google.co.id/books/reader?id=lb3KwEeSkiUC&hl=&printsec=frontcover&output=reader&source=gbs_api\",\n" +
                "    \"accessViewStatus\": \"SAMPLE\",\n" +
                "    \"quoteSharingAllowed\": false\n" +
                "   },\n" +
                "   \"searchInfo\": {\n" +
                "    \"textSnippet\": \"b Online dengan \\u003cb\\u003eAndroid Android\\u003c/b\\u003e merupakan sistem operasi mobile berbasis \\u003cbr\\u003e\\nkernel Linux yang dikembangkan oleh \\u003cb\\u003eAndroid\\u003c/b\\u003e Inc dan kemudian diakuisisi oleh \\u003cbr\\u003e\\nGoogle. Sistem operasi ini bersifat open source sehingga para programmer \\u003cbr\\u003e\\ndapat&nbsp;...\"\n" +
                "   }\n" +
                "  },\n" +
                "  {\n" +
                "   \"kind\": \"books#volume\",\n" +
                "   \"id\": \"39Jlo3nZ7HIC\",\n" +
                "   \"etag\": \"YzlRS4FU+zs\",\n" +
                "   \"selfLink\": \"https://www.googleapis.com/books/v1/volumes/39Jlo3nZ7HIC\",\n" +
                "   \"volumeInfo\": {\n" +
                "    \"title\": \"Android 3. 0 Animations\",\n" +
                "    \"subtitle\": \"Beginner's Guide\",\n" +
                "    \"authors\": [\n" +
                "     \"Alex Shaw\"\n" +
                "    ],\n" +
                "    \"publisher\": \"Packt Publishing Ltd\",\n" +
                "    \"publishedDate\": \"2011-11-01\",\n" +
                "    \"description\": \"Written in Packt's Beginner's Guide series, this book takes a step-by-step approach with each chapter made up of three to five tutorials that introduce and explain different animation concepts. All concepts are explained with real-world examples that are fun to read and work with. If you are familiar with developing Android applications and want to bring your apps to life by adding smashing animations, then this book is for you. The book assumes that you are comfortable with Java development and have familiarity with creating Android Views in XML and Java. The tutorials assume that you will want to work with Eclipse, but you can work just as well with your preferred development tools.\",\n" +
                "    \"industryIdentifiers\": [\n" +
                "     {\n" +
                "      \"type\": \"ISBN_13\",\n" +
                "      \"identifier\": \"9781849515290\"\n" +
                "     },\n" +
                "     {\n" +
                "      \"type\": \"ISBN_10\",\n" +
                "      \"identifier\": \"1849515298\"\n" +
                "     }\n" +
                "    ],\n" +
                "    \"readingModes\": {\n" +
                "     \"text\": true,\n" +
                "     \"image\": true\n" +
                "    },\n" +
                "    \"pageCount\": 304,\n" +
                "    \"printType\": \"BOOK\",\n" +
                "    \"categories\": [\n" +
                "     \"Computers\"\n" +
                "    ],\n" +
                "    \"maturityRating\": \"NOT_MATURE\",\n" +
                "    \"allowAnonLogging\": true,\n" +
                "    \"contentVersion\": \"2.2.2.0.preview.3\",\n" +
                "    \"panelizationSummary\": {\n" +
                "     \"containsEpubBubbles\": false,\n" +
                "     \"containsImageBubbles\": false\n" +
                "    },\n" +
                "    \"imageLinks\": {\n" +
                "     \"smallThumbnail\": \"http://books.google.com/books/content?id=39Jlo3nZ7HIC&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api\",\n" +
                "     \"thumbnail\": \"http://books.google.com/books/content?id=39Jlo3nZ7HIC&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api\"\n" +
                "    },\n" +
                "    \"language\": \"en\",\n" +
                "    \"previewLink\": \"http://books.google.co.id/books?id=39Jlo3nZ7HIC&printsec=frontcover&dq=android&hl=&cd=5&source=gbs_api\",\n" +
                "    \"infoLink\": \"https://play.google.com/store/books/details?id=39Jlo3nZ7HIC&source=gbs_api\",\n" +
                "    \"canonicalVolumeLink\": \"https://market.android.com/details?id=book-39Jlo3nZ7HIC\"\n" +
                "   },\n" +
                "   \"saleInfo\": {\n" +
                "    \"country\": \"ID\",\n" +
                "    \"saleability\": \"FOR_SALE\",\n" +
                "    \"isEbook\": true,\n" +
                "    \"listPrice\": {\n" +
                "     \"amount\": 308496.0,\n" +
                "     \"currencyCode\": \"IDR\"\n" +
                "    },\n" +
                "    \"retailPrice\": {\n" +
                "     \"amount\": 215947.0,\n" +
                "     \"currencyCode\": \"IDR\"\n" +
                "    },\n" +
                "    \"buyLink\": \"https://play.google.com/store/books/details?id=39Jlo3nZ7HIC&rdid=book-39Jlo3nZ7HIC&rdot=1&source=gbs_api\",\n" +
                "    \"offers\": [\n" +
                "     {\n" +
                "      \"finskyOfferType\": 1,\n" +
                "      \"listPrice\": {\n" +
                "       \"amountInMicros\": 3.08496E11,\n" +
                "       \"currencyCode\": \"IDR\"\n" +
                "      },\n" +
                "      \"retailPrice\": {\n" +
                "       \"amountInMicros\": 2.15947E11,\n" +
                "       \"currencyCode\": \"IDR\"\n" +
                "      }\n" +
                "     }\n" +
                "    ]\n" +
                "   },\n" +
                "   \"accessInfo\": {\n" +
                "    \"country\": \"ID\",\n" +
                "    \"viewability\": \"PARTIAL\",\n" +
                "    \"embeddable\": true,\n" +
                "    \"publicDomain\": false,\n" +
                "    \"textToSpeechPermission\": \"ALLOWED\",\n" +
                "    \"epub\": {\n" +
                "     \"isAvailable\": true\n" +
                "    },\n" +
                "    \"pdf\": {\n" +
                "     \"isAvailable\": true\n" +
                "    },\n" +
                "    \"webReaderLink\": \"http://books.google.co.id/books/reader?id=39Jlo3nZ7HIC&hl=&printsec=frontcover&output=reader&source=gbs_api\",\n" +
                "    \"accessViewStatus\": \"SAMPLE\",\n" +
                "    \"quoteSharingAllowed\": false\n" +
                "   },\n" +
                "   \"searchInfo\": {\n" +
                "    \"textSnippet\": \"Written in Packt&#39;s Beginner&#39;s Guide series, this book takes a step-by-step approach with each chapter made up of three to five tutorials that introduce and explain different animation concepts.\"\n" +
                "   }\n" +
                "  },\n" +
                "  {\n" +
                "   \"kind\": \"books#volume\",\n" +
                "   \"id\": \"V-gtAgAAQBAJ\",\n" +
                "   \"etag\": \"aY0aQZ6m1SQ\",\n" +
                "   \"selfLink\": \"https://www.googleapis.com/books/v1/volumes/V-gtAgAAQBAJ\",\n" +
                "   \"volumeInfo\": {\n" +
                "    \"title\": \"Voice Application Development for Android\",\n" +
                "    \"authors\": [\n" +
                "     \"Michael F. McTear\",\n" +
                "     \"Zoraida Callejas\"\n" +
                "    ],\n" +
                "    \"publisher\": \"Packt Publishing Ltd\",\n" +
                "    \"publishedDate\": \"2013-12-11\",\n" +
                "    \"description\": \"This book will give beginners an introduction to building voice-based applications on Android. It will begin by covering the basic concepts and will build up to creating a voice-based personal assistant. By the end of this book, you should be in a position to create your own voice-based applications on Android from scratch in next to no time.Voice Application Development for Android is for all those who are interested in speech technology and for those who, as owners of Android devices, are keen to experiment with developing voice apps for their devices. It will also be useful as a starting point for professionals who are experienced in Android application development but who are not familiar with speech technologies and the development of voice user interfaces. Some background in programming in general, particularly in Java, is assumed.\",\n" +
                "    \"industryIdentifiers\": [\n" +
                "     {\n" +
                "      \"type\": \"ISBN_13\",\n" +
                "      \"identifier\": \"9781783285303\"\n" +
                "     },\n" +
                "     {\n" +
                "      \"type\": \"ISBN_10\",\n" +
                "      \"identifier\": \"1783285303\"\n" +
                "     }\n" +
                "    ],\n" +
                "    \"readingModes\": {\n" +
                "     \"text\": true,\n" +
                "     \"image\": true\n" +
                "    },\n" +
                "    \"pageCount\": 134,\n" +
                "    \"printType\": \"BOOK\",\n" +
                "    \"categories\": [\n" +
                "     \"Computers\"\n" +
                "    ],\n" +
                "    \"maturityRating\": \"NOT_MATURE\",\n" +
                "    \"allowAnonLogging\": true,\n" +
                "    \"contentVersion\": \"1.3.4.0.preview.3\",\n" +
                "    \"panelizationSummary\": {\n" +
                "     \"containsEpubBubbles\": false,\n" +
                "     \"containsImageBubbles\": false\n" +
                "    },\n" +
                "    \"imageLinks\": {\n" +
                "     \"smallThumbnail\": \"http://books.google.com/books/content?id=V-gtAgAAQBAJ&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api\",\n" +
                "     \"thumbnail\": \"http://books.google.com/books/content?id=V-gtAgAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api\"\n" +
                "    },\n" +
                "    \"language\": \"en\",\n" +
                "    \"previewLink\": \"http://books.google.co.id/books?id=V-gtAgAAQBAJ&printsec=frontcover&dq=android&hl=&cd=6&source=gbs_api\",\n" +
                "    \"infoLink\": \"https://play.google.com/store/books/details?id=V-gtAgAAQBAJ&source=gbs_api\",\n" +
                "    \"canonicalVolumeLink\": \"https://market.android.com/details?id=book-V-gtAgAAQBAJ\"\n" +
                "   },\n" +
                "   \"saleInfo\": {\n" +
                "    \"country\": \"ID\",\n" +
                "    \"saleability\": \"FOR_SALE\",\n" +
                "    \"isEbook\": true,\n" +
                "    \"listPrice\": {\n" +
                "     \"amount\": 251346.0,\n" +
                "     \"currencyCode\": \"IDR\"\n" +
                "    },\n" +
                "    \"retailPrice\": {\n" +
                "     \"amount\": 170915.0,\n" +
                "     \"currencyCode\": \"IDR\"\n" +
                "    },\n" +
                "    \"buyLink\": \"https://play.google.com/store/books/details?id=V-gtAgAAQBAJ&rdid=book-V-gtAgAAQBAJ&rdot=1&source=gbs_api\",\n" +
                "    \"offers\": [\n" +
                "     {\n" +
                "      \"finskyOfferType\": 1,\n" +
                "      \"listPrice\": {\n" +
                "       \"amountInMicros\": 2.51346E11,\n" +
                "       \"currencyCode\": \"IDR\"\n" +
                "      },\n" +
                "      \"retailPrice\": {\n" +
                "       \"amountInMicros\": 1.70915E11,\n" +
                "       \"currencyCode\": \"IDR\"\n" +
                "      }\n" +
                "     }\n" +
                "    ]\n" +
                "   },\n" +
                "   \"accessInfo\": {\n" +
                "    \"country\": \"ID\",\n" +
                "    \"viewability\": \"PARTIAL\",\n" +
                "    \"embeddable\": true,\n" +
                "    \"publicDomain\": false,\n" +
                "    \"textToSpeechPermission\": \"ALLOWED\",\n" +
                "    \"epub\": {\n" +
                "     \"isAvailable\": true\n" +
                "    },\n" +
                "    \"pdf\": {\n" +
                "     \"isAvailable\": true\n" +
                "    },\n" +
                "    \"webReaderLink\": \"http://books.google.co.id/books/reader?id=V-gtAgAAQBAJ&hl=&printsec=frontcover&output=reader&source=gbs_api\",\n" +
                "    \"accessViewStatus\": \"SAMPLE\",\n" +
                "    \"quoteSharingAllowed\": false\n" +
                "   },\n" +
                "   \"searchInfo\": {\n" +
                "    \"textSnippet\": \"This book will give beginners an introduction to building voice-based applications on Android.\"\n" +
                "   }\n" +
                "  },\n" +
                "  {\n" +
                "   \"kind\": \"books#volume\",\n" +
                "   \"id\": \"hoFI5pxjGesC\",\n" +
                "   \"etag\": \"qMgFmH6PJwU\",\n" +
                "   \"selfLink\": \"https://www.googleapis.com/books/v1/volumes/hoFI5pxjGesC\",\n" +
                "   \"volumeInfo\": {\n" +
                "    \"title\": \"ANDROID A PROGRAMMERS GUIDE\",\n" +
                "    \"authors\": [\n" +
                "     \"J. F. DiMarzio\"\n" +
                "    ],\n" +
                "    \"publisher\": \"McGraw Hill Professional\",\n" +
                "    \"publishedDate\": \"2008-08-14\",\n" +
                "    \"description\": \"Master the Android mobile development platform Build compelling Java-based mobile applications using the Android SDK and the Eclipse open-source software development platform. Android: A Programmer's Guide shows you, step-by-step, how to download and set up all of the necessary tools, build and tune dynamic Android programs, and debug your results. Discover how to provide web and chat functions, interact with the phone dialer and GPS devices, and access the latest Google services. You'll also learn how to create custom Content Providers and database-enable your applications using SQLite. Install and configure Java, Eclipse, and Android plugin Create Android projects from the Eclipse UI or command line Integrate web content, images, galleries, and sounds Deploy menus, progress bars, and auto-complete functions Trigger actions using Android Intents, Filters, and Receivers Implement GPS, Google Maps, Google Earth, and GTalk Build interactive SQLite databases, calendars, and notepads Test applications using the Android Emulator and Debug Bridge\",\n" +
                "    \"industryIdentifiers\": [\n" +
                "     {\n" +
                "      \"type\": \"ISBN_10\",\n" +
                "      \"identifier\": \"0071599894\"\n" +
                "     },\n" +
                "     {\n" +
                "      \"type\": \"ISBN_13\",\n" +
                "      \"identifier\": \"9780071599894\"\n" +
                "     }\n" +
                "    ],\n" +
                "    \"readingModes\": {\n" +
                "     \"text\": true,\n" +
                "     \"image\": true\n" +
                "    },\n" +
                "    \"pageCount\": 400,\n" +
                "    \"printType\": \"BOOK\",\n" +
                "    \"categories\": [\n" +
                "     \"Computers\"\n" +
                "    ],\n" +
                "    \"averageRating\": 3.0,\n" +
                "    \"ratingsCount\": 11,\n" +
                "    \"maturityRating\": \"NOT_MATURE\",\n" +
                "    \"allowAnonLogging\": true,\n" +
                "    \"contentVersion\": \"2.14.5.0.preview.3\",\n" +
                "    \"panelizationSummary\": {\n" +
                "     \"containsEpubBubbles\": false,\n" +
                "     \"containsImageBubbles\": false\n" +
                "    },\n" +
                "    \"imageLinks\": {\n" +
                "     \"smallThumbnail\": \"http://books.google.com/books/content?id=hoFI5pxjGesC&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api\",\n" +
                "     \"thumbnail\": \"http://books.google.com/books/content?id=hoFI5pxjGesC&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api\"\n" +
                "    },\n" +
                "    \"language\": \"en\",\n" +
                "    \"previewLink\": \"http://books.google.co.id/books?id=hoFI5pxjGesC&printsec=frontcover&dq=android&hl=&cd=7&source=gbs_api\",\n" +
                "    \"infoLink\": \"https://play.google.com/store/books/details?id=hoFI5pxjGesC&source=gbs_api\",\n" +
                "    \"canonicalVolumeLink\": \"https://market.android.com/details?id=book-hoFI5pxjGesC\"\n" +
                "   },\n" +
                "   \"saleInfo\": {\n" +
                "    \"country\": \"ID\",\n" +
                "    \"saleability\": \"FOR_SALE\",\n" +
                "    \"isEbook\": true,\n" +
                "    \"listPrice\": {\n" +
                "     \"amount\": 396901.0,\n" +
                "     \"currencyCode\": \"IDR\"\n" +
                "    },\n" +
                "    \"retailPrice\": {\n" +
                "     \"amount\": 277831.0,\n" +
                "     \"currencyCode\": \"IDR\"\n" +
                "    },\n" +
                "    \"buyLink\": \"https://play.google.com/store/books/details?id=hoFI5pxjGesC&rdid=book-hoFI5pxjGesC&rdot=1&source=gbs_api\",\n" +
                "    \"offers\": [\n" +
                "     {\n" +
                "      \"finskyOfferType\": 1,\n" +
                "      \"listPrice\": {\n" +
                "       \"amountInMicros\": 3.96901E11,\n" +
                "       \"currencyCode\": \"IDR\"\n" +
                "      },\n" +
                "      \"retailPrice\": {\n" +
                "       \"amountInMicros\": 2.77831E11,\n" +
                "       \"currencyCode\": \"IDR\"\n" +
                "      }\n" +
                "     }\n" +
                "    ]\n" +
                "   },\n" +
                "   \"accessInfo\": {\n" +
                "    \"country\": \"ID\",\n" +
                "    \"viewability\": \"PARTIAL\",\n" +
                "    \"embeddable\": true,\n" +
                "    \"publicDomain\": false,\n" +
                "    \"textToSpeechPermission\": \"ALLOWED_FOR_ACCESSIBILITY\",\n" +
                "    \"epub\": {\n" +
                "     \"isAvailable\": true,\n" +
                "     \"acsTokenLink\": \"http://books.google.co.id/books/download/ANDROID_A_PROGRAMMERS_GUIDE-sample-epub.acsm?id=hoFI5pxjGesC&format=epub&output=acs4_fulfillment_token&dl_type=sample&source=gbs_api\"\n" +
                "    },\n" +
                "    \"pdf\": {\n" +
                "     \"isAvailable\": true,\n" +
                "     \"acsTokenLink\": \"http://books.google.co.id/books/download/ANDROID_A_PROGRAMMERS_GUIDE-sample-pdf.acsm?id=hoFI5pxjGesC&format=pdf&output=acs4_fulfillment_token&dl_type=sample&source=gbs_api\"\n" +
                "    },\n" +
                "    \"webReaderLink\": \"http://books.google.co.id/books/reader?id=hoFI5pxjGesC&hl=&printsec=frontcover&output=reader&source=gbs_api\",\n" +
                "    \"accessViewStatus\": \"SAMPLE\",\n" +
                "    \"quoteSharingAllowed\": false\n" +
                "   },\n" +
                "   \"searchInfo\": {\n" +
                "    \"textSnippet\": \"Master the Android mobile development platform Build compelling Java-based mobile applications using the Android SDK and the Eclipse open-source software development platform.\"\n" +
                "   }\n" +
                "  },\n" +
                "  {\n" +
                "   \"kind\": \"books#volume\",\n" +
                "   \"id\": \"pZlF7lQY5SQC\",\n" +
                "   \"etag\": \"Q8ugNNd1m5g\",\n" +
                "   \"selfLink\": \"https://www.googleapis.com/books/v1/volumes/pZlF7lQY5SQC\",\n" +
                "   \"volumeInfo\": {\n" +
                "    \"title\": \"Pro Android Web Apps\",\n" +
                "    \"subtitle\": \"Develop for Android using HTML5, CSS3 & JavaScript\",\n" +
                "    \"authors\": [\n" +
                "     \"Damon Oehlman\",\n" +
                "     \"Sébastien Blanc\"\n" +
                "    ],\n" +
                "    \"publisher\": \"Apress\",\n" +
                "    \"publishedDate\": \"2011-02-22\",\n" +
                "    \"description\": \"Developing applications for Android and other mobile devices using web technologies is now well within reach. When the capabilities of HTML5 are combined with CSS3 and JavaScript, web application developers have an opportunity to develop compelling mobile applications using familiar tools. Not only is it possible to build mobile web apps that feel as good as native apps, but to also write an application once and have it run a variety of different devices. While the HTML5 specification is still evolving, there is a lot that can be used right now to build mobile web apps. Mobile web apps are now starting to provide many of the features that were once only available to native-language-based apps in Java, Objective-C, etc. Pro Android Web Apps teaches developers already familiar with web application development, how to code and structure a web app for use on the Android mobile platform. Understand both the why and how of mobile web app development, focusing on the Android platform. Learn how to structure mobile web apps through a number of practical, real-world application examples. Discover what cloud platforms such as Google AppEngine have to offer Android web apps, for both hosting web apps and providing device to cloud data synchronization solutions. Get a real picture of the status of HTML5 on Android and other mobile devices, including some things to watch out for when building your own applications. Understand the capabilities of the web application stack, and how to complement those with native bridging frameworks such as PhoneGap to access native features of the device. Gain an understanding of the different UI frameworks that are available for building mobile web apps. Learn how to include mapping and leverage location-based services in mobile web apps to create engaging mobile experiences. Enable social integration with your Android web app and gain access to millions of potential users. After reading this book, you will not only have a greater understanding of the world of web apps on Android, but also how to leverage additional tools and frameworks to increase the reach of your mobile web apps. Additionally, through the practical samples in the book you will have been given solid exposure of where both the opportunities and challenges lie when building mobile apps the web way. What you’ll learn What Android web apps can do, and when to use web development rather than native development to create an application. How to use existing JavaScript and CSS frameworks to create rich mobile user interfaces. When to use HTML5 and when to use a native bridging framework to access native Android functionality. Connext with cloud services and APIs to build engaging location based services and games. Enable social integration with your Android web app and gain access to millions of potential users. Who this book is for This book is targeted at web developers looking to transfer their skills over to mobile application development. Readers will understand that Android is continuing to gain momentum in the marketplace and will want to build an application specifically for that platform. They will have a strong desire to use web technologies rather than the native tools to build applications, either due to personal taste or to gain cross-platform mobile portability for the majority of their application code. Table of Contents Getting Started Building a Mobile HTML Entry Form HTML5 Storage APIs Constructing a Multipage App Synchronizing with the Cloud Competing with Native Apps Exploring Interactivity Location Based Services and Mobile Mapping Native Bridging with PhoneGap Integrating with Social APIs Mobile UI Frameworks Compared Polishing and Packaging an App for Release The Future of Mobile Computing Appendix: Debugging Android Web Apps\",\n" +
                "    \"industryIdentifiers\": [\n" +
                "     {\n" +
                "      \"type\": \"ISBN_13\",\n" +
                "      \"identifier\": \"9781430232766\"\n" +
                "     },\n" +
                "     {\n" +
                "      \"type\": \"ISBN_10\",\n" +
                "      \"identifier\": \"1430232765\"\n" +
                "     }\n" +
                "    ],\n" +
                "    \"readingModes\": {\n" +
                "     \"text\": true,\n" +
                "     \"image\": true\n" +
                "    },\n" +
                "    \"pageCount\": 392,\n" +
                "    \"printType\": \"BOOK\",\n" +
                "    \"categories\": [\n" +
                "     \"Computers\"\n" +
                "    ],\n" +
                "    \"maturityRating\": \"NOT_MATURE\",\n" +
                "    \"allowAnonLogging\": true,\n" +
                "    \"contentVersion\": \"0.5.1.0.preview.3\",\n" +
                "    \"imageLinks\": {\n" +
                "     \"smallThumbnail\": \"http://books.google.com/books/content?id=pZlF7lQY5SQC&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api\",\n" +
                "     \"thumbnail\": \"http://books.google.com/books/content?id=pZlF7lQY5SQC&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api\"\n" +
                "    },\n" +
                "    \"language\": \"en\",\n" +
                "    \"previewLink\": \"http://books.google.co.id/books?id=pZlF7lQY5SQC&printsec=frontcover&dq=android&hl=&cd=8&source=gbs_api\",\n" +
                "    \"infoLink\": \"http://books.google.co.id/books?id=pZlF7lQY5SQC&dq=android&hl=&source=gbs_api\",\n" +
                "    \"canonicalVolumeLink\": \"https://books.google.com/books/about/Pro_Android_Web_Apps.html?hl=&id=pZlF7lQY5SQC\"\n" +
                "   },\n" +
                "   \"saleInfo\": {\n" +
                "    \"country\": \"ID\",\n" +
                "    \"saleability\": \"NOT_FOR_SALE\",\n" +
                "    \"isEbook\": false\n" +
                "   },\n" +
                "   \"accessInfo\": {\n" +
                "    \"country\": \"ID\",\n" +
                "    \"viewability\": \"PARTIAL\",\n" +
                "    \"embeddable\": true,\n" +
                "    \"publicDomain\": false,\n" +
                "    \"textToSpeechPermission\": \"ALLOWED\",\n" +
                "    \"epub\": {\n" +
                "     \"isAvailable\": true,\n" +
                "     \"acsTokenLink\": \"http://books.google.co.id/books/download/Pro_Android_Web_Apps-sample-epub.acsm?id=pZlF7lQY5SQC&format=epub&output=acs4_fulfillment_token&dl_type=sample&source=gbs_api\"\n" +
                "    },\n" +
                "    \"pdf\": {\n" +
                "     \"isAvailable\": true,\n" +
                "     \"acsTokenLink\": \"http://books.google.co.id/books/download/Pro_Android_Web_Apps-sample-pdf.acsm?id=pZlF7lQY5SQC&format=pdf&output=acs4_fulfillment_token&dl_type=sample&source=gbs_api\"\n" +
                "    },\n" +
                "    \"webReaderLink\": \"http://books.google.co.id/books/reader?id=pZlF7lQY5SQC&hl=&printsec=frontcover&output=reader&source=gbs_api\",\n" +
                "    \"accessViewStatus\": \"SAMPLE\",\n" +
                "    \"quoteSharingAllowed\": false\n" +
                "   },\n" +
                "   \"searchInfo\": {\n" +
                "    \"textSnippet\": \"After reading this book, you will not only have a greater understanding of the world of web apps on Android, but also how to leverage additional tools and frameworks to increase the reach of your mobile web apps.\"\n" +
                "   }\n" +
                "  },\n" +
                "  {\n" +
                "   \"kind\": \"books#volume\",\n" +
                "   \"id\": \"tsRhkvo80iUC\",\n" +
                "   \"etag\": \"N8aEp6zoQP4\",\n" +
                "   \"selfLink\": \"https://www.googleapis.com/books/v1/volumes/tsRhkvo80iUC\",\n" +
                "   \"volumeInfo\": {\n" +
                "    \"title\": \"The Android Invasion\",\n" +
                "    \"authors\": [\n" +
                "     \"Christopher Black\"\n" +
                "    ],\n" +
                "    \"publisher\": \"Yearling\",\n" +
                "    \"publishedDate\": \"1984-06-01\",\n" +
                "    \"description\": \"In this multiple plot adventure, readers and their robot companion must stop armies of attacking androids before they conquer the galaxy.\",\n" +
                "    \"industryIdentifiers\": [\n" +
                "     {\n" +
                "      \"type\": \"ISBN_10\",\n" +
                "      \"identifier\": \"0440400813\"\n" +
                "     },\n" +
                "     {\n" +
                "      \"type\": \"ISBN_13\",\n" +
                "      \"identifier\": \"9780440400813\"\n" +
                "     }\n" +
                "    ],\n" +
                "    \"readingModes\": {\n" +
                "     \"text\": false,\n" +
                "     \"image\": false\n" +
                "    },\n" +
                "    \"pageCount\": 117,\n" +
                "    \"printType\": \"BOOK\",\n" +
                "    \"categories\": [\n" +
                "     \"Androids\"\n" +
                "    ],\n" +
                "    \"maturityRating\": \"NOT_MATURE\",\n" +
                "    \"allowAnonLogging\": false,\n" +
                "    \"contentVersion\": \"0.0.1.0.preview.0\",\n" +
                "    \"imageLinks\": {\n" +
                "     \"smallThumbnail\": \"http://books.google.com/books/content?id=tsRhkvo80iUC&printsec=frontcover&img=1&zoom=5&source=gbs_api\",\n" +
                "     \"thumbnail\": \"http://books.google.com/books/content?id=tsRhkvo80iUC&printsec=frontcover&img=1&zoom=1&source=gbs_api\"\n" +
                "    },\n" +
                "    \"language\": \"en\",\n" +
                "    \"previewLink\": \"http://books.google.co.id/books?id=tsRhkvo80iUC&q=android&dq=android&hl=&cd=9&source=gbs_api\",\n" +
                "    \"infoLink\": \"http://books.google.co.id/books?id=tsRhkvo80iUC&dq=android&hl=&source=gbs_api\",\n" +
                "    \"canonicalVolumeLink\": \"https://books.google.com/books/about/The_Android_Invasion.html?hl=&id=tsRhkvo80iUC\"\n" +
                "   },\n" +
                "   \"saleInfo\": {\n" +
                "    \"country\": \"ID\",\n" +
                "    \"saleability\": \"NOT_FOR_SALE\",\n" +
                "    \"isEbook\": false\n" +
                "   },\n" +
                "   \"accessInfo\": {\n" +
                "    \"country\": \"ID\",\n" +
                "    \"viewability\": \"NO_PAGES\",\n" +
                "    \"embeddable\": false,\n" +
                "    \"publicDomain\": false,\n" +
                "    \"textToSpeechPermission\": \"ALLOWED\",\n" +
                "    \"epub\": {\n" +
                "     \"isAvailable\": false\n" +
                "    },\n" +
                "    \"pdf\": {\n" +
                "     \"isAvailable\": false\n" +
                "    },\n" +
                "    \"webReaderLink\": \"http://books.google.co.id/books/reader?id=tsRhkvo80iUC&hl=&printsec=frontcover&output=reader&source=gbs_api\",\n" +
                "    \"accessViewStatus\": \"NONE\",\n" +
                "    \"quoteSharingAllowed\": false\n" +
                "   },\n" +
                "   \"searchInfo\": {\n" +
                "    \"textSnippet\": \"In this multiple plot adventure, readers and their robot companion must stop armies of attacking androids before they conquer the galaxy.\"\n" +
                "   }\n" +
                "  },\n" +
                "  {\n" +
                "   \"kind\": \"books#volume\",\n" +
                "   \"id\": \"xlp6NE2NWecC\",\n" +
                "   \"etag\": \"8QMi9wDfeIY\",\n" +
                "   \"selfLink\": \"https://www.googleapis.com/books/v1/volumes/xlp6NE2NWecC\",\n" +
                "   \"volumeInfo\": {\n" +
                "    \"title\": \"Android\",\n" +
                "    \"subtitle\": \"Earth Book One of the Android Saga\",\n" +
                "    \"authors\": [\n" +
                "     \"Paul J. Ward\"\n" +
                "    ],\n" +
                "    \"publisher\": \"Strategic Book Publishing\",\n" +
                "    \"publishedDate\": \"2012-11-01\",\n" +
                "    \"description\": \"Humans and androids are learning to co-exist as equals, but there are many opposers who seek to subvert their own creations and rule with absolute power ...Humans, with their android creations loyally at their side, have colonised the Moon and Mars. The Earth and Colonies Defence Service (ECDS) keeps the colonies and space lanes safe. On Earth, the Android Protectorate League, led by the enigmatic android leader Traviod Selius, campaign for android rights legislation. However, they are strongly opposed by the Anti-Android Faction (AAF). Following the approval of the Human and Android Cohabitation Act, ECDS Chief of Operations Nakaar Bacvor and co-conspirators form the military wing of the AAF. The AAF attack the Moon and Mars colonies, but are repulsed by ECDS forces. On Earth, with the AAF seemingly defeated, humans and androids unite to create the city of Utopia. Threats from a reformed, more powerful AAF emerges and the crew of the ECDS flagship Harmonia must formulate a defence. The mysterious Evolved Androids appear on Earth with a sytoid child called Eirini, who has strange powers. Utopian Enforcement officer Rul Calibra becomes her unlikely guardian and protector.Humankind's destiny hangs in the balance in the first series installment ANDROID: Earth - Book One of the ANDROID Saga. Paul J. Ward was born in 1969 in Lincolnshire, England, on the day of the historic Apollo 11 moon landing. He has been fascinated by space exploration and technological developments his whole life. This is his first novel. Publisher's website: http: //sbpra.com/PaulJWar\",\n" +
                "    \"industryIdentifiers\": [\n" +
                "     {\n" +
                "      \"type\": \"ISBN_13\",\n" +
                "      \"identifier\": \"9781618971241\"\n" +
                "     },\n" +
                "     {\n" +
                "      \"type\": \"ISBN_10\",\n" +
                "      \"identifier\": \"1618971247\"\n" +
                "     }\n" +
                "    ],\n" +
                "    \"readingModes\": {\n" +
                "     \"text\": false,\n" +
                "     \"image\": false\n" +
                "    },\n" +
                "    \"pageCount\": 446,\n" +
                "    \"printType\": \"BOOK\",\n" +
                "    \"categories\": [\n" +
                "     \"Fiction\"\n" +
                "    ],\n" +
                "    \"maturityRating\": \"NOT_MATURE\",\n" +
                "    \"allowAnonLogging\": false,\n" +
                "    \"contentVersion\": \"preview-1.0.0\",\n" +
                "    \"imageLinks\": {\n" +
                "     \"smallThumbnail\": \"http://books.google.com/books/content?id=xlp6NE2NWecC&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api\",\n" +
                "     \"thumbnail\": \"http://books.google.com/books/content?id=xlp6NE2NWecC&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api\"\n" +
                "    },\n" +
                "    \"language\": \"en\",\n" +
                "    \"previewLink\": \"http://books.google.co.id/books?id=xlp6NE2NWecC&printsec=frontcover&dq=android&hl=&cd=10&source=gbs_api\",\n" +
                "    \"infoLink\": \"http://books.google.co.id/books?id=xlp6NE2NWecC&dq=android&hl=&source=gbs_api\",\n" +
                "    \"canonicalVolumeLink\": \"https://books.google.com/books/about/Android.html?hl=&id=xlp6NE2NWecC\"\n" +
                "   },\n" +
                "   \"saleInfo\": {\n" +
                "    \"country\": \"ID\",\n" +
                "    \"saleability\": \"NOT_FOR_SALE\",\n" +
                "    \"isEbook\": false\n" +
                "   },\n" +
                "   \"accessInfo\": {\n" +
                "    \"country\": \"ID\",\n" +
                "    \"viewability\": \"PARTIAL\",\n" +
                "    \"embeddable\": true,\n" +
                "    \"publicDomain\": false,\n" +
                "    \"textToSpeechPermission\": \"ALLOWED\",\n" +
                "    \"epub\": {\n" +
                "     \"isAvailable\": false\n" +
                "    },\n" +
                "    \"pdf\": {\n" +
                "     \"isAvailable\": false\n" +
                "    },\n" +
                "    \"webReaderLink\": \"http://books.google.co.id/books/reader?id=xlp6NE2NWecC&hl=&printsec=frontcover&output=reader&source=gbs_api\",\n" +
                "    \"accessViewStatus\": \"SAMPLE\",\n" +
                "    \"quoteSharingAllowed\": false\n" +
                "   },\n" +
                "   \"searchInfo\": {\n" +
                "    \"textSnippet\": \"Paul J. Ward was born in 1969 in Lincolnshire, England, on the day of the historic Apollo 11 moon landing. He has been fascinated by space exploration and technological developments his whole life. This is his first novel.\"\n" +
                "   }\n" +
                "  }\n" +
                " ]\n" +
                "}";

        books = JSONUtils.parseJSON(jsonStr);

        RecyclerView bookRecyclerView = (RecyclerView) findViewById(R.id.recycler_book);
        bookRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        bookRecyclerView.addItemDecoration(new SpacesItemDecoration(getResources().getDimensionPixelSize(R.dimen.default_space)));

        BookRecyclerAdapter adapter = new BookRecyclerAdapter(this, books);
        bookRecyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_search_result, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);

        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.onActionViewExpanded();
        searchView.setQuery(query, true);
        searchView.clearFocus();
        return super.onCreateOptionsMenu(menu);
    }
}
