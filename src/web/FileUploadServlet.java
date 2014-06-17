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
            //step1 创建一个工厂类的实例，该实例
            //为解析器提供了缺省的配置。
            DiskFileItemFactory factory =
                new DiskFileItemFactory();
            //step2 创建一个解析器
            ServletFileUpload sfu =
                new ServletFileUpload(factory);
            String contentType=request.getContentType();
            System.out.println("contentType:"+contentType);
            //step3 使用解析器解析
            try {
                //解析之后，会将表单中的数据转换成一个个
                //FileItem对象。一个表单域中的数据对应于一个
                //FileItem对象。
                List<FileItem> items =
                    sfu.parseRequest(request);
                //step4 遍历items集合
                for(int i=0;i<items.size();i++){
                    FileItem item = items.get(i);
                    //读表单域中的数据时，要区分表单域的类型
                    if(item.isFormField()){
                        //普通表单域
                        String username = item.getString();
                        System.out.println("username:"+username);
                        
                        String name = new String(username.getBytes("ISO8859-1"),"UTF-8");   
                        System.out.println("name:"+name);
                    }else{
                    	String fileContentType = item.getContentType();  
                    	System.out.println("fileContentType:"+fileContentType);
                    	
                        //文件上传表单域
                        ServletContext sctx =
                            getServletContext();
                        String path = sctx.getRealPath("upload");
                        //获得文件名
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