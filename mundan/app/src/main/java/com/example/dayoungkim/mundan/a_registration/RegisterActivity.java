package com.example.dayoungkim.mundan.a_registration;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dayoungkim.mundan.MainActivity;
import com.example.dayoungkim.mundan.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {


    private AlertDialog dialog;
    private EditText id, nick, password, password2;
    private Button btn_regist;
    private ProgressBar loading;
    private static String URL_REGIST = "http://115.68.222.192/register.php";


    //비번 재확인 엑스
    ImageView setImage;

    private boolean validate = false;

    SessionManager sessionManager;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        id = findViewById(R.id.nick_text);
        nick = findViewById(R.id.id_text);
        password = findViewById(R.id.password);
        password2 = findViewById(R.id.password2);
        btn_regist = findViewById(R.id.registerButton);


        //이용약관 보기(밑줄)
        final TextView agree = (TextView)findViewById(R.id.agree);
        //이용약관 라디오그룹
        final RadioGroup rg = (RadioGroup)findViewById(R.id.radioGroup1);
        //비번 재확인 엑스
        setImage = (ImageView)findViewById(R.id.setImage);



        //회원가입 버튼에 셋온클릭리스너
        btn_regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String userId = id.getText().toString();
                String userNick = nick.getText().toString();
                String userPassword = password.getText().toString();
                String userPassword2 = password2.getText().toString();



                //ID 중복체크를 했는지 확인함
                if(!validate){
                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                    dialog = builder.setMessage("아이디 중복검사를 해주세요.")
                            .setNegativeButton("OK", null)
                            .create();
                    dialog.show();
                    return;
                }

                //한칸이라도 빠뜨렸을 경우
                if(userId.equals("")||userPassword.equals("")||userPassword2.equals("")||userNick.equals(""))
                {
                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                    dialog = builder.setMessage("모든 정보를 입력해주세요.")
                            .setNegativeButton("OK", null)
                            .create();
                    dialog.show();
                    return;
                }


                //이용약관 비동의 눌렀을 때
                int rb = rg.getCheckedRadioButtonId();

                switch (rb) {
                    case R.id.agree_yes :
                    {
                        break;
                    }
                    case R.id.agree_no :
                    {
                        AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                        dialog = builder.setMessage("이용약관 동의는 필수항목입니다.")
                                .setPositiveButton("확인",null)
                                .create();
                        dialog.show();
                        return;
                    }
                }


                //비번 확인 재확인 다를 때
                if(!userPassword.equals(userPassword2))
                {
                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                    dialog = builder.setMessage("비밀번호가 일치하지 않습니다.")
                            .setPositiveButton("확인",null)
                            .create();
                    dialog.show();

                    return;
                }


                Regist();
                Log.d("회원가입", "1");

            }
        });



        //비번 재확인 엑스
        password2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(password.getText().toString().equals(password2.getText().toString())) {
                    setImage.setImageResource(R.drawable.ok);
                }
                else                {
                    setImage.setImageResource(R.drawable.stop);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });




        //이용약관동의
        agree.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                AlertDialog.Builder agree_builder = new AlertDialog.Builder(RegisterActivity.this);
                dialog = agree_builder.setMessage("제 1 장 총 칙\n" +
                        "제 1 조 (목적)\n" +
                        "이 약관은 저작권 인증업무를 수행함에 있어 법령 및 위원회의 저작권 인증업무 처리규정이 정한 사항 외에 ‘저작권인증시스템(이하 ‘시스템’)의 이용’ 서비스와 관련하여 회원과 한국저작권위원회(이하 ‘위원회’)와의 중요사항을 정하는 것을 목적으로 합니다.\n" +
                        "\n" +
                        "제 2 조 (약관의 효력 및 적용과 변경)\n" +
                        "(1) 약관의 효력 및 적용\n" +
                        "\n" +
                        "이 약관은 본 시스템 게시판을 통하여 이용자에게 공시함으로써 효력을 발생합니다. 이 약관에 동의하고 회원가입을 한 회원은 약관에 동의한 시점부터 동의한 약관의 적용을 받고 약관의 변경이 있을 경우에는 변경의 효력이 발생한 시점부터 변경된 약관의 적용을 받습니다.\n" +
                        "\n" +
                        "(2) 약관의 변경\n" +
                        "\n" +
                        "위원회는 필요하다고 인정되는 경우 이 약관을 변경할 수 있으며, 약관이 변경된 경우에는 지체 없이 공시합니다. 회원은 변경된 약관 사항에 동의하지 않으면 서비스 이용을 중단하고 이용계약을 해지할 수 있습니다.\n" +
                        "\n" +
                        "제 3 조 (약관규정 외의 사항에 관한 준칙)\n" +
                        "현 약관에 규정되지 않은 사항에 대해서는 이 시스템으로 처리되는 서비스 규칙 또는 안내에 따릅니다.\n" +
                        "\n" +
                        "제 4 조(용어의 정의)\n" +
                        "(1) 회원 : 서비스 이용계약을 체결한 자\n" +
                        "\n" +
                        "(2) 아이디(ID) : 회원 식별과 서비스 이용을 위하여 회원이 선정한 문자와 숫자의 조합으로서 온라인상의 조합을 말한다.\n" +
                        "\n" +
                        "(3) 패스워드(password) : 회원의 정보보호와 회원만의 이용을 위한 문자와 숫자로 이루어진 조합을 말한다.\n" +
                        "\n" +
                        "(4) 운영자 : 이 시스템의 운영과 전반적인 관리를 담당하는 자 \n" +
                        "\n" +
                        "\n" +
                        "제 2 장 서비스 이용계약\n" +
                        "제 5 조 (이용계약의 성립)\n" +
                        "이용자가 시스템에 의해 제시되는 양식에 따라 자신의 정보를 입력하고 이용계약 체결을 신청하면 시스템이 전자적으로 회원등록을 승인함으로써 위원회의 서비스를 제공받으실 수 있습니다.\n" +
                        "\n" +
                        "제 6 조 (이용신청 시 기재사항)\n" +
                        "회원은 가입 시 다음에 규정하는 사항을 필수적으로 입력하셔야 위원회의의 승인을 받으실 수 있습니다.\n" +
                        "\n" +
                        "(1) 이름(실명인증)\n" +
                        "\n" +
                        "(2) 주민등록번호 또는 법인등록번호(또는 이에 갈음하는 고유번호 등)\n" +
                        "\n" +
                        "(3) 아이디(ID)\n" +
                        "\n" +
                        "(4) 패스워드(password)\n" +
                        "\n" +
                        "(5) 연락처(전화번호 및 주소 등)\n" +
                        "\n" +
                        "(6) e-mail address\n" +
                        "\n" +
                        "제 13 조 (회원의 게시물)\n" +
                        "(1) 게시물이라 함은 시스템 내에 회원이 올린 글, 사진, 그림, 각종 파일과 링크, 각종 덧글 등의 정보를 의미합니다.\n" +
                        "\n" +
                        "(2) 서비스 내에 게시한 게시물로 인해 회원 개인에게 발생하는 손실이나 문제는 전적으로 회원 개인의 책임이며, 위원회는 이에 대한 어떠한 책임도 부담하지 않습니다. 만일 회원이 타인의 저작권 등을 침해하였음을 이유로 위원회가 타인으로부터 손해배상청구 등 이의제기를 받은 경우 회원은 위원회의 면책을 위하여 노력하여야 하며, 위원회가 면책되지 못한 경우 회원은 그로 인해 위원회에 발생한 모든 손해를 부담하여야 합니다.\n" +
                        "\n" +
                        "(3) 회원은 다음 각 호에 해당하는 내용물을 게시하거나 전달할 수 없으며, 위원회는 서비스 내에 존재하는 내용물이 다음 각 호에 해당한다고 판단되는 경우 사전 통지 없이 삭제, 이동 또는 등록 거부할 수 있습니다. 또한, 위원회는 게시물에 관련된 세부 이용지침을 별도로 정하여 시행할 수 있으며, 회원은 그 지침에 따라 각종 게시물을 등록하거나 삭제하여야 합니다.\n" +
                        "\n" +
                        "① 위원회 또는 제3자를 비방하거나 중상모략으로 명예를 손상시키는 내용인 경우\n" +
                        "\n" +
                        "② 공공질서 및 미풍양속에 위반되는 내용의 정보, 문장, 도형 등의 유포에 해당하는 경우\n" +
                        "\n" +
                        "③ 범죄적 행위에 결부된다고 인정되는 내용인 경우\n" +
                        "\n" +
                        "④ 위원회의 저작권, 제3자의 저작권 등 기타 권리를 침해하는 내용인 경우\n" +
                        "\n" +
                        "⑤ 위원회가 정하는 게시기간을 초과한 경우\n" +
                        "\n" +
                        "⑥ 위원회가 제공하는 서비스의 성격에 부합되지 않는 내용인 경우\n" +
                        "\n" +
                        "⑦ 불필요하거나 승인되지 않은 광고, 판촉물을 게재하는 경우\n" +
                        "\n" +
                        "⑧ 타인의 아이디(고유번호), 성명 등을 무단으로 도용하여 작성한 내용이거나, 타인이 입력한 정보를 무단으로 위·변조한 내용인 경우\n" +
                        "\n" +
                        "⑨ 동일한 내용을 중복하여 다수 게시하는 등 게시의 목적에 어긋나는 경우\n" +
                        "\n" +
                        "⑩ 기타 관계 법령 및 운영 지침 등에 위반된다고 판단되는 경우\n" +
                        "\n" +
                        "제 14 조 (게시물의 저작권 등)\n" +
                        "(1) 위원회가 작성하여 사이트에서 제공하는 저작물에 대한 저작권, 기타 지적재산권은 위원회에 귀속됩니다.\n" +
                        "\n" +
                        "(2) 회원 게시물에 대한 저작권은 해당 저작권자에게 귀속됩니다.\n" +
                        "\n" +
                        "(3) 회원은 서비스를 이용함으로써 얻은 정보를 위원회의 사전 승낙 없이 복제, 출판, 전송, 배포, 방송 기타 방법에 의하여 영리목적으로 이용하거나 제3자에게 이용하게 할 수 없으며, 게시물에 대한 저작권 침해는 관계 법령의 적용을 받습니다.\n" +
                        "\n" +
                        "(4) 회원은 자신이 창작, 등록한 게시물에 대하여 위원회 또는 위원회가 허락한 제3자가 서비스를 운영, 전시, 홍보하기 위한 목적으로 다음의 각 호에 해당하는 행위를 할 수 있도록, 사용료 없는 비독점적 사용권을 위원회에 부여합니다.\n" +
                        "\n" +
                        "① 서비스 내에서 회원 게시물의 복제, 수정, 개조, 전송, 전시, 배포, 2차적 저작물 및 편집 저작물 작성\n" +
                        "\n" +
                        "② 시스템 서비스 내에서 회원 게시물을 전시, 배포\n" +
                        "\n" +
                        "③ 시스템 서비스를 홍보하기 위한 목적으로 미디어, 통신사 등에게 회원의 게시물 내용을 제공, 사용하게 하는 것. 단, 이 경우 회원의 동의 없이 개인정보를 제공하지 않습니다.\n" +
                        "\n" +
                        "(5) 위원회는 회원이 탈퇴한 후에도 본조 제4항의 사용권을 유지합니다. 그러나 탈퇴로 인한 커뮤니티 폐쇄 시 사용자가 이미 동의한 대로 커뮤니티 관련 사용자 게시물에 대하여는 책임지지 않습니다.\n" +
                        "\n" +
                        "(6) 위원회는 회원의 개별 동의 없이 본조 제4항에서 규정하는 목적 이외의 목적으로 회원의 게시물을 사용하지 않습니다. 다만, 시스템의 폐쇄, 양도, 시스템 통합 등의 사유로 원래의 게시물의 내용을 변경하지 않고 게시물의 게시 위치를 변경할 수는 있습니다.\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "제 4 장 계약당사자의 의무\n" +
                        "제 15 조 (시스템 운영상의 의무)\n" +
                        "(1) 개인정보 관리 : 위원회는 서비스를 제공하면서 알게 된 회원의 신상정보를 본인의 승낙 없이 외부로 유출하거나 제3자에게 배포, 제공하지 않습니다. 단, 서비스의 운영과 관련하여 법령 또는 정부 고시로 정한 사항 및 적법한 절차를 거친 국가기관의 요구나 수사상 또는 기타 공익을 위하여 필요하다고 인정되는 경우는 예외로 합니다.\n" +
                        "\n" +
                        "(2) 위원회는 회원의 본 서비스 이용을 위하여 노력할 의무가 있으며 회원의 정보 역시 위원회의 시스템 등록 회원에 대한 서비스 향상을 위해 이용합니다.\n" +
                        "\n" +
                        "제 16 조 (회원의 비밀번호 등 관리의무)\n" +
                        "(1) 회원의 관리상 잘못으로 인한 아이디와 패스워드 노출로 인한 피해는 전적으로 회원의 책임입니다.\n" +
                        "\n" +
                        "(2) 회원의 이용권한은 회원 개인에 한정된 것이며 제3자 양도 또는 대여 시 회원의 아이디를 임의로 삭제할 수 있으며, 무단 양도나 대여에 대한 책임은 전적으로 회원이 집니다.\n" +
                        "\n" +
                        "제 17 조 (서비스 전반에 관한 회원의 의무)\n" +
                        "(1) 회원은 본 서비스 이용 시 다음 각 호의 행위를 하여서는 안 됩니다.\n" +
                        "\n" +
                        "① 회원은 이용 신청 또는 변경 시 허위 사실을 기재하거나, 다른 사람의 e-mail을 무단 사용하는 행위를 할 수 없습니다.\n" +
                        "\n" +
                        "② 회원은 본 서비스 이용 중 습득한 정보를 상업적 목적으로 이용하거나 출판, 방송 등을 통하여 허락 없이 제3자에 노출시키는 행위를 할 수 없습니다. 단, 공익 목적이나 회원의 이익을 위하여 필요한 경우에는 당사에게 협의를 요청하고 당사의 허락을 받아야 합니다.\n" +
                        "\n" +
                        "③ 회원은 제3자의 권리나 저작권 등을 침해하는 행위를 할 수 없습니다.\n" +
                        "\n" +
                        "④ 회원은 위원회가 공식적으로 인정한 경우를 제외하고는 서비스를 이용(서비스 내에 광고물을 게시하거나 제3자에게 광고물을 발송하는 방식 등을 포함합니다)하여 상품 또는 용역을 판매하는 영업활동 등의 상행위를 할 수 없으며, 특히 해킹, 광고를 통한 수익, 음란사이트를 통한 상업행위, 상용소프트웨어 불법배포 등을 할 수 없습니다. 이를 위반하여 발생한 상행위의 결과 및 손실, 관계기관에 의한 구속 등 법적 조치 등에 관해서는 위원회가 책임을 지지 않으며, 회원은 이와 같은 행위로 위원회에 손해를 입힌 경우 배상 의무를 집니다.\n" +
                        "\n" +
                        "⑤ 회원은 시스템의 운영을 저해하는 행위를 할 수 없습니다.\n" +
                        "\n" +
                        "⑥ 회원은 중요한 자료를 서비스 내에서 게시하거나 저장할 경우 서비스 이외의 별도의 저장 장치에 해당 자료를 보관해야 합니다.\n" +
                        "\n" +
                        "⑦ 회원은 공공질서를 위반하거나 건전한 사회 미풍양속을 저해하는 정보나 문자, 그림 등을 타인에게 유포시키는 행위를 할 수 없습니다.\n" +
                        "\n" +
                        "(2) 회원은 위원회가 행하는 서비스 향상을 위한 노력에 성실히 협조하여야 합니다.\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +

                        "[부칙]\n" +
                        "이 약관은 2012년 2월 1일부터 시행합니다.")
                        .setNegativeButton("확인", null)
                        .create();
                dialog.show();
            }

        });






        ////// 아이디 중복확인

        //회원가입시 아이디가 사용가능한지 검증하는 부분
        final Button validateButton = (Button)findViewById(R.id.validateButton);
        validateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userID = id.getText().toString();
                if(validate){
//                    return;
                }
                //ID 값을 입력하지 않았다면
                if(userID.equals("")){
                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                    dialog = builder.setMessage("아이디를 입력해주세요")
                            .setPositiveButton("OK", null)
                            .create();
                    dialog.show();
                    return;
                }


                //검증시작
                Response.Listener<String> responseListener = new Response.Listener<String>(){

                    @Override
                    public void onResponse(String response) {
                        try{

                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if(success){//사용할 수 있는 아이디라면

                                id.setEnabled(false);//아이디값을 바꿀 수 없도록 함
                                validate = true;//검증완료
                                id.setTextColor(getResources().getColor(R.color.colorGray));
                                validateButton.setBackgroundColor(getResources().getColor(R.color.colorGray));
                            }else{//사용할 수 없는 아이디라면
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                dialog = builder.setMessage("이미 존재하는 아이디입니다.")
                                        .setNegativeButton("OK", null)
                                        .create();
                                dialog.show();
                            }

                        }
                        catch(Exception e){
                            e.printStackTrace();
                        }
                    }
                };//Response.Listener 완료

                //Volley 라이브러리를 이용해서 실제 서버와 통신을 구현하는 부분 - 아이디 중복체크 버튼 눌렀을 때
                ValidateRequest validateRequest = new ValidateRequest(userID, responseListener);
                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                queue.add(validateRequest);

            }
        });
        ///// 아이디 중복확인 끝




        //닉네임 중복확인
        final Button validate_nick_button = (Button) findViewById(R.id.nick_validate);
        validate_nick_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("검증", "1");
                String userNICK = nick.getText().toString();
                if(validate){
                    Log.d("검증", "2");
                }
                //ID 값을 입력하지 않았다면
                if(userNICK.equals("")){
                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                    dialog = builder.setMessage("닉네임을 입력해주세요")
                            .setPositiveButton("OK", null)
                            .create();
                    dialog.show();
                    return;
                }

                //검증시작
                Response.Listener<String> responseListener2 = new Response.Listener<String>(){

                    @Override
                    public void onResponse(String response2) {
                        try{
                            Log.d("검증", "3");

                            JSONObject jsonResponse2 = new JSONObject(response2);
                            Log.d("검증", "4");
                            boolean success = jsonResponse2.getBoolean("success");
                            if(success){//사용할 수 있는 아이디라면
                                Log.d("검증", "5");

                                nick.setEnabled(false);//아이디값을 바꿀 수 없도록 함
                                validate = true;//검증완료
                                nick.setTextColor(getResources().getColor(R.color.colorGray));
                                validate_nick_button.setBackgroundColor(getResources().getColor(R.color.colorGray));
                            }
                            else{//사용할 수 없는 아이디라면
                                Log.d("검증", "6");
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                dialog = builder.setMessage("이미 존재하는 닉네임입니다.")
                                        .setNegativeButton("OK", null)
                                        .create();
                                dialog.show();
                            }
                        }
                        catch(Exception e){
                            e.printStackTrace();
                            Log.d("검증 ", "캐치 오류");
                        }
                    }
                };//Response.Listener 완료

                //Volley 라이브러리를 이용해서 실제 서버와 통신을 구현하는 부분 - 아이디 중복체크 버튼 눌렀을 때
                ValidateNickRequest validateNickRequest = new ValidateNickRequest(userNICK, responseListener2);
                Log.d("검증", "7");
                RequestQueue queue2 = Volley.newRequestQueue(RegisterActivity.this);
                Log.d("검증", "8");
                queue2.add(validateNickRequest);
                Log.d("검증", "9");

            }
        });




    }







    private void Regist() {


        final String id = this.id.getText().toString().trim();
        final String nick = this.nick.getText().toString().trim();
        final String password = this.password.getText().toString().trim();


        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_REGIST,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try{
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if(success.equals("1")) {




                                Toast.makeText(RegisterActivity.this, "회원가입을 환영합니다!", Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                startActivity(intent);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(RegisterActivity.this, "실패", Toast.LENGTH_SHORT).show();

                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(RegisterActivity.this, "실패", Toast.LENGTH_SHORT).show();
                    }
                })


        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id", id);
                params.put("nick", nick);
                params.put("password", password);

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }


}

