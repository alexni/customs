package ru.customs.service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import ru.customs.entity.FriendlyMessage;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;

public class Firebase {

    private static final String DATABASE_URL = "https://renelogistika.firebaseio.com/";
    private FirebaseDatabase firebaseDatabase;

    public Firebase() {
        Resource resource = new ClassPathResource("renelogistika-firebase-adminsdk-xqbj1-26e7385054.json");
        InputStream is = null;
        try {
            is = resource.getInputStream();
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(is))
                    .setDatabaseUrl(DATABASE_URL)
                    .build();
            FirebaseApp.initializeApp(options);
            firebaseDatabase = FirebaseDatabase.getInstance(DATABASE_URL);

        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        } finally {
            if (is!=null) {
                try {
                    is.close();
                } catch (IOException e) {
                }
            }
        }
    }


    public void update(FriendlyMessage message, String key) {
        try {
            DatabaseReference ref = firebaseDatabase.getReference("messages-"+key);
            final CountDownLatch latch = new CountDownLatch(1);
            ref.push().setValue(message, new DatabaseReference.CompletionListener() {
                @Override
                public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                    if (databaseError != null) {
                        System.out.println("Data could not be saved " + databaseError.getMessage());
                        latch.countDown();
                    } else {
                        System.out.println("Data saved successfully.");
                        latch.countDown();
                    }
                }
            });
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }

    public void close() {
        firebaseDatabase.getApp().delete();
    }
}
