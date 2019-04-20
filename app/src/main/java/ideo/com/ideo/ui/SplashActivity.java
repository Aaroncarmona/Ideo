package ideo.com.ideo.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.util.DiffUtil;
import android.support.v7.util.ListUpdateCallback;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import ideo.com.ideo.util.Util;

public class SplashActivity extends AppCompatActivity {
    private static final String TAG = SplashActivity.class.getSimpleName();


    private LinearLayout root , rootContentTv ;
    private EditText id , name ;
    private Button button ;
    private Button buttonDelete ;
    private Button buttonMerge ;

    private TextView tvLocal , tvRemote ;

    private List<Person> remote = new ArrayList<Person>() {{

    }};

    private List<Person> local = new ArrayList<Person>() {{
        add( new Person(1 , "aaron"));
        add( new Person(2 , "carmona"));
    }};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.root = new LinearLayout(this);
        this.rootContentTv = new LinearLayout(this);

        this.id = new EditText(this);
        this.name = new EditText(this);

        this.button = new Button(this);
        this.buttonDelete = new Button(this);
        this.buttonMerge= new Button(this);

        this.tvLocal = new TextView(this);
        this.tvRemote = new TextView(this);

        root.setLayoutParams( new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT , ViewGroup.LayoutParams.WRAP_CONTENT));
        root.setOrientation(LinearLayout.VERTICAL);

        rootContentTv.setLayoutParams( new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT , ViewGroup.LayoutParams.WRAP_CONTENT));
        rootContentTv.setOrientation(LinearLayout.HORIZONTAL);

        id.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT , ViewGroup.LayoutParams.WRAP_CONTENT));
        name.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT , ViewGroup.LayoutParams.WRAP_CONTENT));

        id.setHint("id");
        name.setHint("name");

        button.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT , ViewGroup.LayoutParams.WRAP_CONTENT));
        button.setText("guardar remoto");

        buttonMerge.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT , ViewGroup.LayoutParams.WRAP_CONTENT));
        buttonMerge.setText("local merge remote");

        buttonDelete.setLayoutParams( new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT , ViewGroup.LayoutParams.WRAP_CONTENT));
        buttonDelete.setText("position remote delete");

        tvLocal.setLayoutParams( new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        tvRemote.setLayoutParams( new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT , ViewGroup.LayoutParams.WRAP_CONTENT));

        rootContentTv.setPadding(10 , 10 , 10 , 10);
        tvLocal.setPadding(10 , 10 , 10 , 10 );
        tvRemote.setPadding(10 , 10 , 10 , 10);

        root.addView(id);
        root.addView(name);
        root.addView(button);
        root.addView(buttonDelete);
        root.addView(buttonMerge);

        rootContentTv.addView(tvLocal);
        rootContentTv.addView(tvRemote);

        root.addView(rootContentTv);

        setRandom();

        button.setOnClickListener( v -> {
            try {
                if (id.getText().toString().equals("")) return;
                if (name.getText().toString().equals("")) return;

                this.remote.add(new Person(Integer.parseInt(id.getText().toString()), name.getText().toString()));

                updateTextView();

                setRandom();

            } catch ( Exception e ){
                Util.toast(getBaseContext() , "no se pudo guardar");
            }

        });

        buttonDelete.setOnClickListener( v -> {
            try {
                if ( id.getText().toString().equals("")) return;
                if ( remote.size() == 0 ) return;

                int number = Integer.parseInt(id.getText().toString());

                remote.remove(getIndex(number));

                updateTextView();
            }catch (Exception e){
                Util.toast(getBaseContext() , e.getMessage());
            }

        });

        buttonMerge.setOnClickListener( v -> {
            merge(local , remote);
            updateTextView();
        });

        setContentView(root);
        updateTextView();
    }

    public int getIndex( int id ){
        for ( int i = 0 ; i < remote.size() ; i++ ){
            if ( remote.get(i).getId() == id ) {
                return i;
            }
        }
        return -1;
    }

    int numberInt = 100;

    public void setRandom(){
        id.setText(String.valueOf(numberInt));
        name.setText(UUID.randomUUID().toString().substring(0 , 3));
        numberInt++;
    }


    public void updateTextView() {
        StringBuilder sbLocal = new StringBuilder();
        StringBuilder sbRemote = new StringBuilder();

        sbLocal.append("local");
        sbLocal.append("\n");
        for ( Person current : local ) {
            sbLocal.append(current.getId() + " : " + current.getName());
            sbLocal.append("\n");
        }

        sbRemote.append("remote");
        sbRemote.append("\n");

        for ( Person current : remote ) {
            sbRemote.append(current.getId() + " : " + current.getName());
            sbRemote.append("\n");
        }

        tvLocal.setText("");
        tvRemote.setText("");

        tvLocal.setText(sbLocal.toString());
        tvRemote.setText(sbRemote.toString());
    }


    public void merge( List<Person> currentData  , List<Person> newData ){
        DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffCallbackCompare(currentData, newData));


        result.dispatchUpdatesTo(new ListUpdateCallback() {
            @Override
            public void onInserted(int position, int count) {
                if ( remote.size() > position - count ) {
                    local.addAll(remote.subList(position , position + count));
                } else {
                    local.addAll(remote.subList(position - count , position));
                }

                Log.d(TAG, "onInserted: ");
            }

            @Override
            public void onRemoved(int position, int count) {
                int i = 0;
                while( i < count ) {
                    local.remove(position);
                    i++;
                }
                Log.d(TAG, "onRemoved: ");
            }

            @Override
            public void onMoved(int fromPosition, int toPosition) {
                Log.d(TAG, "onMoved: ");
                Person current = local.get(fromPosition);
                local.remove(fromPosition);
                local.add(toPosition , current);
            }

            @Override
            public void onChanged(int position, int count, Object payload) {
                Log.d(TAG, "onChanged: ");
            }
        });
    }
}