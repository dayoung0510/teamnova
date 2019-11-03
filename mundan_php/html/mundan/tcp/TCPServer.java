import java.io.BufferedReader;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import java.io.OutputStreamWriter;

import java.io.PrintWriter;

import java.net.ServerSocket;

import java.net.Socket;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import java.util.Vector;

 

public class TCPServer  
{

    public static final int ServerPort = 1342;
    public static final String ServerIP = "115.68.222.192";
    

    public static void main(String[] args) 
    {
    	

    	
    	try
    	{            
        	
            //서버소켓 생성 (포트번호는 안드랑 같아야 함)
            ServerSocket serverSocket = new ServerSocket(ServerPort);
            HashMap<String, Object> hm = new HashMap<String, Object>();
            System.out.println("서버소켓 생성");
            
            while(true)
            {
            	//서버 대기하고 있다가 
                System.out.println("서버 : 접속 대기 중");
                Socket client = serverSocket.accept();
                
                //액셉트 하면 아래 쓰레드 시작함 
                System.out.println("서버 : 유저 발견");
                ChattingThread chattingThread = new ChattingThread(client, hm);
                chattingThread.start();
                
            }
    	}
    	
    	catch(Exception e)
    	{
    		e.printStackTrace();
            System.out.println("유저발견하기 : 캐치");
    	}

    }

}




class ChattingThread extends Thread
{
	private Socket client;
	private HashMap<String, Object> hm;
	private BufferedReader in;
	Vector <ChattingThread> connectList;
	private String nickName;
	
	
	public ChattingThread(Socket client, HashMap<String, Object> hm)
	{
		this.client = client;
		this.hm = hm;
		this.connectList = connectList;
		
		
        try 
        {
    		//버퍼리더는 스캐너랑 비슷한데 많은 양 입력받을 때 좋음 
            in = new BufferedReader(

            //인풋스트림리더 = 바이트 스트림을 문자 스트림으로 변환
            //소켓에서 들어오는 내용을 받음
            new InputStreamReader(client.getInputStream()));
           
            //출력하는 역할인데, 프린트라이터 안에 버퍼라이터 씀으로써 두개 장점 모두 취할 수 있음
            //PrintWriter는 다양한 출력함수를 제공함으로써 파일출력을 편하게 해준다.
            //BufferedWriter는 버퍼를 사용하여 통해 좀 더 효율적인 파일쓰기를 지원한다.
            PrintWriter out = new PrintWriter(new BufferedWriter
            (new OutputStreamWriter(client.getOutputStream())),true);
            
            //처음 입력 받은 게 아이디가 된다
            //두 번째 입력부터는 채팅 내용으로 인식됨
            nickName = in.readLine();
            System.out.println("리드라인으로 받아온 아이디 " + nickName);
            
            //처음에 aaa로 고정해서 넣었더니 1개뿐이었음 (새로 만들어지는 게 아니라 덮어씌워져서)
            //그래서 랜덤함수 넣어놓음 -나중에 안드로이드 닉네임으로 고치기
            synchronized(hm)
            {
//            	Random random = new Random();
//            	String randomStr = String.valueOf((char) ((int) (random.nextInt(26)) + 97));
//            	
//            	//해시맵에 넣기
            	hm.put(this.nickName, out);
            	System.out.println("해시맵에 들어간 아이디 " + this.nickName);            	
            }
            
            
        }
        
        catch (Exception e) 
        {
            System.out.println("서버 : 에러2");
            e.printStackTrace();
        }
        
		
	}
	
	
    public void run()
    {
    	
        //새로운 유저 들어오면 여기부터 실행됨
        try 
        {     
        	//유저 접속 끊길 때까지 반복해라
        	while (true)
        	{                		
                //스트링 str = 클라로부터 들어온 내용 스트링형으로 바꾼 것
                String str = in.readLine();
                
                if (str == null)
                {
                	break;
                }
                else
                {
                    System.out.println("서버가 받은 내용 : " + str );
                }
                


                //프린트라이터 객체 생성해가지고
                PrintWriter out = new PrintWriter(new BufferedWriter
                (new OutputStreamWriter(client.getOutputStream())),true);


                //브로드캐스트에서 따온거
                synchronized (hm) 
                {
                	
                	//해시맵에 오브젝트 부분 
                	//컬렉션 객체는 여러 원소들을 담을 수 있는 자료구조임
                    Collection<Object> collection = hm.values();
                    
                    //Iterator는  컬렉션에 저장되어 있는 요소들을 읽어오는 방법
                    Iterator<?> iter = collection.iterator();
                    


                    while(iter.hasNext()) 
                    {
                    	out = (PrintWriter)iter.next();
                        out.println(str);                       
                    }
                    
                }
                
              
                //만약 클라이언트에서 quit 입력받으면 와일문 빠져나가라 (그럼 파이널리 수행함)
                if(str.equals("quit"))
                    break;                   
        	}
        	
        	
            System.out.println("요기 추가해봤는데욤");
        	
        } 
        
        //안드 별안간 종료됐을 때 뜨는 부분
        catch (Exception e) 
        {
            try 
            {
            	//소켓 클로즈
				client.close();
			} 
            
            catch (IOException e1) 
            {
                System.out.println("종료 1");
//				e1.printStackTrace();
			}
//            e.printStackTrace();
            System.out.println("사용자 접속종료");
            
            
        }
        
        //파이널리는 트라이나 캐치 상관없이 무조건 수행되는 부분
        //안드에서 별안간 종료되면 캐치 후 여기뜨고, quit 입력했을 시 여기 뜬다
        finally 
        {
            try 
            {
            	//소켓 닫아라 
				client.close();
                System.out.println("파이널리 -소켓클로즈");
			} 
            
            catch (IOException e) 
            {
				e.printStackTrace();
			}
            
            System.out.println("서버 : Done");
            
        }
    	
    }
	
    
   

    
}






