
import java.io.*;

public class select {
	static int filecount = 0;
    static int number = 0;
	
	public static void method2(String path) throws IOException{//������һ����·����һ����Ҫ���ҵ�����
		File file = new File(path);
		//file.listFiles()��ǰĿ¼�����е��ļ����ļ���
		File files[] = file.listFiles();
		//�����жϵ�ǰĿ¼������鲻��Ϊ��
		if(files!=null){
			//��ǿforѭ��������ǰ���Ŀ¼
			for(File f:files){
				//�ж����ļ���
				if(f.isDirectory()){
				   //�ݹ飺method2(����·��������)�����Լ�
				   method2(f.getAbsolutePath()); 
				}
				//�ж����ļ�
				if(f.isFile()){
					//�ڵ�ǰĿ¼�µ������ļ��в��ң�������Ŀ¼���Ƶ����ֵ>-1,��������Ŀ¼��·��
					if(f.getName().indexOf(".java")>-1){
						filecount++;
	            		BufferedReader br = new BufferedReader(new FileReader(f));
	            		String line = "";
	            	    while((line=br.readLine())!=null){
	            	    	number++;
	            	    }
	            	    br.close();
						System.out.println(f.getAbsolutePath());
					}
				}
			}
		}
		
	}
	public static void main(String[] args) {

		try {
			method2("D:\\WORKSPACE\\StudentManager");
			System.out.println("�ļ�����:"+filecount);
          System.out.println("����:"+number);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
