package com.example.btllogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SigUp extends AppCompatActivity {
    EditText signupName, signupUsername, signupEmail, signupPassword,signupconfirmPassword;
    TextView loginRedirectText;
    Button signupButton;
    FirebaseDatabase database;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sig_up);

        signupName = findViewById(R.id.signup_name);
        signupEmail = findViewById(R.id.signup_email);
        signupUsername = findViewById(R.id.signup_username);
        signupPassword = findViewById(R.id.signup_password);
        signupconfirmPassword = findViewById(R.id.signup_confirmpassword);
        loginRedirectText = findViewById(R.id.loginRedirectText);
        signupButton = findViewById(R.id.signup_button);

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkDataEntered();
            }
        });
        loginRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SigUp.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
    boolean isEmail(EditText text) {
        CharSequence email = text.getText().toString();
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }
    boolean isPasswordValid(EditText passwordField) {
        CharSequence password = passwordField.getText().toString();
        // Thêm các điều kiện kiểm tra mật khẩu ở đây, ví dụ: mật khẩu phải có ít nhất 8 ký tự
        return password.length() >= 8;
    }

    boolean doPasswordsMatch(EditText passwordField, EditText confirmPasswordField) {
        CharSequence password = passwordField.getText().toString();
        CharSequence confirmPassword = confirmPasswordField.getText().toString();
        // Kiểm tra xem mật khẩu và xác nhận mật khẩu có khớp nhau không
        return password.equals(confirmPassword);
    }

    void checkDataEntered() {
        if (isEmpty(signupName)) {
            Toast t = Toast.makeText(this, "You must enter first name to register!", Toast.LENGTH_SHORT);
            t.show();
        }

        if (isEmpty(signupUsername)) {
            signupUsername.setError("Không được bỏ troongs!");
        }

        if (isEmail(signupEmail) == false) {
            signupEmail.setError("Sai định danạng email!");
        }
        if (!isPasswordValid(signupPassword)) {
            // Đặt thông báo lỗi nếu mật khẩu không đủ mạnh
            signupPassword.setError("Mật khẩu phải lơn hơn 8 kí tự!");
        }

        if (!doPasswordsMatch(signupPassword, signupconfirmPassword)) {
            // Đặt thông báo lỗi nếu mật khẩu và xác nhận mật khẩu không khớp nhau
            signupconfirmPassword.setError("Mật khẩu không khớp!");
        }
        else if(isEmpty(signupName)==false&&isEmpty(signupUsername)==false&&isEmail(signupEmail)==true&&isPasswordValid(signupPassword)&&doPasswordsMatch(signupPassword,signupconfirmPassword)) {
            database = FirebaseDatabase.getInstance();
            reference = database.getReference("users");
            String name = signupName.getText().toString();
            String email = signupEmail.getText().toString();
            String username = signupUsername.getText().toString();
            String password = signupPassword.getText().toString();
            HelperClass helperClass = new HelperClass(name, email, username, password);
            reference.child(username).setValue(helperClass);
            Toast.makeText(SigUp.this, "Đăng kí thành công!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(SigUp.this, MainActivity.class);
            startActivity(intent);
        }
    }
}