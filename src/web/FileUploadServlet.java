    package web;
    import java.io.File;
    import java.io.IOException;
    import java.util.List;
    import javax.servlet.ServletContext;
    import javax.servlet.ServletException;
    import javax.servlet.http.HttpServlet;
    import javax.servlet.http.HttpServletRequest;
    import javax.servlet.http.HttpServletResponse;
    import org.apache.commons.fileupload.FileItem;
    import org.apache.commons.fileupload.disk.DiskFileItemFactory;
    import org.apache.commons.fileupload.servlet.ServletFileUpload;
    public class FileUploadServlet extends HttpServlet {
    	
            public void service(HttpServletRequest request,
                    HttpServletResponse response)
                throws ServletException, IOException {
            //step1 ����һ���������ʵ������ʵ��
            //Ϊ�������ṩ��ȱʡ�����á�
            DiskFileItemFactory factory =
                new DiskFileItemFactory();
            //step2 ����һ��������
            ServletFileUpload sfu =
                new ServletFileUpload(factory);
            String contentType=request.getContentType();
            System.out.println("contentType:"+contentType);
            //step3 ʹ�ý���������
            try {
                //����֮�󣬻Ὣ���е�����ת����һ����
                //FileItem����һ�������е����ݶ�Ӧ��һ��
                //FileItem����
                List<FileItem> items =
                    sfu.parseRequest(request);
                //step4 ����items����
                for(int i=0;i<items.size();i++){
                    FileItem item = items.get(i);
                    //�������е�����ʱ��Ҫ���ֱ��������
                    if(item.isFormField()){
                        //��ͨ����
                        String username = item.getString();
                        System.out.println("username:"+username);
                        
                        String name = new String(username.getBytes("ISO8859-1"),"UTF-8");   
                        System.out.println("name:"+name);
                    }else{
                    	String fileContentType = item.getContentType();  
                    	System.out.println("fileContentType:"+fileContentType);
                    	
                        //�ļ��ϴ�����
                        ServletContext sctx =
                            getServletContext();
                        String path = sctx.getRealPath("upload");
                        //����ļ���
                        String fileName = item.getName();
                        File a=new File(fileName);
                        String fileName2=a.getName();
                        File file = new File(path + "\\" + fileName2);
                        
                        String whatGetString=item.getFieldName();
                        System.out.println("whatGetString:"+whatGetString);
                        System.out.println("file:"+file.getAbsolutePath());
                        item.write(file);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }