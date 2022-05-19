package com.sb.firebase.serviceImpl;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import com.sb.firebase.model.User;
import com.sb.firebase.service.CrudService;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class CrudServiceImpl implements CrudService {

    /* Since we have already configured the app using the
           service account key, we don't have to worry about the
           authorization anymore */
    private final Firestore dbFireStore = FirestoreClient.getFirestore();

    @Override
    public String createUser(User user){
        ApiFuture<WriteResult> collectionApiFuture = dbFireStore.collection("crud_user").document(user.getName()).set(user);
        try{
            return collectionApiFuture.get().getUpdateTime().toString();
        }catch(ExecutionException ee){
            return "Execution Exception :: " + ee;
        }catch(InterruptedException ie){
            return "Interrupted Exception :: " + ie;
        }
    }

    @Override
    public User getUser(String documentId) {
        DocumentReference documentReference = dbFireStore.collection("crud_user").document(documentId);
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        try{
            DocumentSnapshot document = future.get();
            // Now converting in to POJO
            User user;
            if(document.exists()){
                user = document.toObject(User.class);
                return user;
            }
        } catch(ExecutionException ee){
            System.out.println("Execution Exception :: " + ee);
        } catch(InterruptedException ie){
            System.out.println("Interrupted Exception :: " + ie);
        }
        return null;
    }

    @Override
    public String updateUser(String documentId, User user) {
        return null;
    }

    @Override
    public String deleteUser(String documentId) {
        ApiFuture<WriteResult> writeResult = dbFireStore.collection("crud_user").document(documentId).delete();
        return "Successfully deleted :: " + documentId;
    }

    private Firestore connectFirestoreDB() {
        return FirestoreClient.getFirestore();
    }
}
