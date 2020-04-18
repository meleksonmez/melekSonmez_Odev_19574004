package com.example.application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SendEmail extends AppCompatActivity {

    EditText editTextto, editTextSubject, editTextMessage;
    Button send, attachment;
    String attachmentFile;
    Uri URI = null;
    private static final int PICK_FROM_GALLERY = 101;
    int columnIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_email);

        editTextto = (EditText) findViewById(R.id.toEditText);
        editTextSubject = (EditText) findViewById(R.id.subjectEditText);
        editTextMessage = (EditText) findViewById(R.id.messageEditText);

        send = (Button) findViewById(R.id.sendButton);
        attachment = (Button) findViewById(R.id.attachment_button);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String to = editTextto.getText().toString();
                String subject = editTextSubject.getText().toString();
                String message = editTextMessage.getText().toString();

                Intent email = new Intent(Intent.ACTION_SEND);
                email.putExtra(Intent.EXTRA_EMAIL, new String[]{to});
                email.putExtra(Intent.EXTRA_SUBJECT, subject);
                email.putExtra(Intent.EXTRA_TEXT, message);
                if (URI != null) {
                    email.putExtra(Intent.EXTRA_STREAM, URI);
                }

                email.setType("message/rfc822");

                startActivity(Intent.createChooser(email, "Choose an Email client."));
            }
        });

        attachment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.putExtra("return-data", true);
                startActivityForResult(Intent.createChooser(intent, "Complete action using"), PICK_FROM_GALLERY);
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_FROM_GALLERY && resultCode == RESULT_OK) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
            cursor.moveToFirst();
            columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            attachmentFile = cursor.getString(columnIndex);
            Log.e("Attachment Path:", attachmentFile);
            URI = Uri.parse("file://" + attachmentFile);
            cursor.close();
        }

    }

}
