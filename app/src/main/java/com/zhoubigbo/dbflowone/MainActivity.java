package com.zhoubigbo.dbflowone;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.zhoubigbo.dbflowone.model.User;
import com.zhoubigbo.dbflowone.model.User_Table;

import java.util.List;

public class MainActivity extends Activity {

    private Button btnInsert, btnDelete, btnUpdate, btnSelect;
    private TextView txtShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnInsert = (Button) findViewById(R.id.btn_insert);
        btnDelete = (Button) findViewById(R.id.btn_delete);
        btnUpdate = (Button) findViewById(R.id.btn_update);
        btnSelect = (Button) findViewById(R.id.btn_select);
        btnInsert.setOnClickListener(new OnInsert());
        btnDelete.setOnClickListener(new OnDelete());
        btnUpdate.setOnClickListener(new OnUpdate());
        btnSelect.setOnClickListener(new OnSelect());
        txtShow = (TextView) findViewById(R.id.txt_show);
    }

    class OnInsert implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            User user = new User();
            user.setName("张三");
            user.setAge(18);
            user.save();
        }
    }

    class OnDelete implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            User user = new User();
            user.setTableId(1);
            user.delete();
        }
    }

    class OnUpdate implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            User user = new User();
            user.setName("李四");
            user.setTableId(2);
            user.update();
        }
    }

    class OnSelect implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            List<User> userList = SQLite.select().from(User.class).where(User_Table.name.eq("张三")).queryList();
            txtShow.setText(userList.size() + "个");
        }
    }
}
