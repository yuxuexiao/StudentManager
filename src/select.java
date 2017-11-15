
import java.io.*;

public class select {
	static int filecount = 0;
    static int number = 0;
	
	public static void method2(String path) throws IOException{//参数：一个是路径，一个是要查找的内容
		File file = new File(path);
		//file.listFiles()当前目录下所有的文件和文件夹
		File files[] = file.listFiles();
		//首先判断当前目录这个数组不能为空
		if(files!=null){
			//增强for循环遍历当前这个目录
			for(File f:files){
				//判断是文件夹
				if(f.isDirectory()){
				   //递归：method2(绝对路径，内容)调用自己
				   method2(f.getAbsolutePath()); 
				}
				//判断是文件
				if(f.isFile()){
					//在当前目录下的所有文件中查找，如果这个目录名称的这个值>-1,则输出这个目录的路径
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
			System.out.println("文件个数:"+filecount);
          System.out.println("行数:"+number);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
