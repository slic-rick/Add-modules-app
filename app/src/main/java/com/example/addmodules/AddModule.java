package com.example.addmodules;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import Model.Module;

public class AddModule extends AppCompatActivity {

    private static final String TAG = "AddModule";

    private Spinner spinner;
    private EditText moduleName;
    private Button saveButton;
    private TextView course;            // course name

    private int selectedField;
    private String courseId;
    private String courseName;

    //database ref
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference coursesRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_module);

        spinner = findViewById(R.id.yearCategory);
        moduleName = findViewById(R.id.edit_text_moduleName);
        saveButton = findViewById(R.id.saveButton);
        course = findViewById(R.id.courseName);

        Intent intent = getIntent();
        courseName = intent.getStringExtra("courseName");
        courseId = intent.getStringExtra("courseId");
        selectedField = intent.getIntExtra("selected", 0);

        course.setText(courseName);

        setUpCollection();

        Log.d(TAG, "onCreate: GOT COURSE NAME AND COURSE ID"+ courseName + "course id"+ courseId);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String yearCategory = spinner.getSelectedItem().toString();
                String module = moduleName.getText().toString();


                if (!yearCategory.isEmpty() && !module.isEmpty() && !courseId.isEmpty()){

                    if (yearCategory.equalsIgnoreCase("1st year")){
                        int year = 1;
                        addModule(year,module,courseId,courseName);
                        Log.d(TAG, "onClick: ADDING YEAR ONE MODULE");

                    } else if (yearCategory.equalsIgnoreCase("2nd year")){
                        int year = 2;
                        addModule(year,module,courseId,courseName);
                        Log.d(TAG, "onClick: ADDING TWO FOUR MODULE");

                    }else if (yearCategory.equalsIgnoreCase("3rd year")){
                        
                        int year = 3;
                        addModule(year,module,courseId,courseName);
                        Log.d(TAG, "onClick: ADDING YEAR THREE MODULE");

                    }else if (yearCategory.equalsIgnoreCase("4th year")){

                        int year = 4;
                        addModule(year,module,courseId,courseName);
                        Log.d(TAG, "onClick: ADDING YEAR FOUR MODULE");

                    } else {
                        Toast.makeText(AddModule.this, "Didn't get the right year category", Toast.LENGTH_SHORT).show();
                    }

                    Log.d(TAG, "onClick: Added course "+ module);
//                        addModule(yearCategory,module,courseId,courseName);
                }
            }

        });
    }

    private void addModule(int yearCategory, String module, String courseId, String courseName) {

        if (!module.isEmpty() && !courseId.isEmpty() && !courseName.isEmpty() && yearCategory > 0){

            Module newModule = new Module(courseName,courseId,module,yearCategory);
            coursesRef.add(newModule).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.d(TAG, "onFailure: ERROR"+ e.getMessage());
                    Toast.makeText(AddModule.this, "An error occurred "+ e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                @Override
                public void onComplete(@NonNull Task<DocumentReference> task) {
                    Toast.makeText(AddModule.this, "COURSE ADDED!!!", Toast.LENGTH_SHORT).show();
                    moduleName.setText("");
                }
            });

        } else{
            Toast.makeText(this, "Some info is missing", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "addModule: PRINTING MODULE FIELDS YEAR CATEGORY: "+ yearCategory+ "MODULE NAME: "+ module+" COURSE ID:"+ courseId+" COURSE NAME:"+ courseName);
        }

    }

    private void setUpCollection() {

        Log.d(TAG, "setUpCollection: METHOD CALLED");

        if (selectedField != 0) {
            if (selectedField == 1) {
                Log.d(TAG, "setUpCollection: COMPUTING_COURSES COLLECTION REF INITIALISED");
                coursesRef = db.collection("Courses").document(
                        "NUST_courses").collection("computing_course")
                        .document(courseId).collection("Modules");

            } else if (selectedField == 2) {
                Log.d(TAG, "setUpCollection: ENGINEERING_COURSES COLLECTION REF INITIALISED");
                coursesRef = db.collection("Courses").document(
                        "NUST_courses").collection("Engeneering")
                        .document(courseId).collection("Modules");
            } else if (selectedField == 3) {
                Log.d(TAG, "setUpCollection: HEALTH AND APPLIED_COURSES COLLECTION REF INITIALISED");
                coursesRef = db.collection("Courses").document(
                        "NUST_courses").collection("Health and Applied Sciences")
                        .document(courseId).collection("Modules");

            } else if (selectedField == 4) {
                Log.d(TAG, "setUpCollection: HUMAN SCIENCES_COURSES COLLECTION REF INITIALISED");
                coursesRef = db.collection("Courses").document(
                        "NUST_courses").collection("Human Sciences")
                        .document(courseId).collection("Modules");

            } else if (selectedField == 5) {
                Log.d(TAG, "setUpCollection: MANAGEMENT SCIENCES_COURSES COLLECTION REF INITIALISED");

                coursesRef = db.collection("Courses").document(
                        "NUST_courses").collection("Management Sciences")
                        .document(courseId).collection("Modules");

            } else if (selectedField == 6) {
                Log.d(TAG, "setUpCollection: NATURAL RESOURCES_COURSES COLLECTION REF INITIALISED");
                coursesRef = db.collection("Courses").document(
                        "NUST_courses").collection("Natural Resources and Spatial Sciences")
                        .document(courseId).collection("Modules");

            } else {
                Log.d(TAG, "setUpCollection: DIDN'T GET THE RIGHT SELECTED NUMBER!!!");
                Toast.makeText(this, "setUpCollection: DIDN'T GET THE RIGHT SELECTED FIELD NUMBER!!!", Toast.LENGTH_SHORT).show();
            }
        } else {
            Log.d(TAG, "setUpCollection: SELECTED FIELD IS NULL, ERROR OCCURRED, Collection NOT INIT..");
            Toast.makeText(this, "Collection  ref not initialised SELECTED FIELD == 0", Toast.LENGTH_SHORT).show();
        }
    }

}
