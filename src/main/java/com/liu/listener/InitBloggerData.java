package com.liu.listener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.liu.Utils.DateUtil;
import com.liu.model.Blog;
import com.liu.model.BlogType;
import com.liu.model.Blogger;
import com.liu.model.Link;
import com.liu.model.PageBean;
import com.liu.service.BlogService;
import com.liu.service.BlogTypeService;
import com.liu.service.BloggerService;
import com.liu.service.LinkService;

 /** 
 * @ClassName: InitBloggerData 
 * @author: lyd
 * @date: 2017��10��10�� ����12:32:35 
 * @describe:�����������ڸտ�ʼ���ز��͵���Ϣ
 */
@Component
public class InitBloggerData implements ServletContextListener,ApplicationContextAware{
	private static ApplicationContext applicationContext;

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		InitBloggerData.applicationContext=applicationContext;
		
	}

	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		 
	}

	public void contextInitialized(ServletContextEvent sce) {//��ʼ������
	applicationContext=new ClassPathXmlApplicationContext("classpath:ApplicationContext.xml");//����spring����
	ServletContext application=sce.getServletContext();//��ȡServlet������
	BlogService blogService=(BlogService) applicationContext.getBean("blogService");//��ȡbean
	BlogTypeService blogTypeService=(BlogTypeService) applicationContext.getBean("blogTypeService");
	LinkService linkService=(LinkService) applicationContext.getBean("linkService");
	BloggerService bloggerService=(BloggerService) applicationContext.getBean("bloggerService");
	int pageSize=5;//��һҳ�Ĳ��͸���
	int blogcounts=blogService.getAllBlog().size();//��ȡ��������
	Map<String,Object> map=new HashMap<String, Object>();
	int totalPage=(int)Math.ceil(blogcounts*1.0/pageSize);//��ȡ�ܵ�ҳ��
	map.put("start",0);
	map.put("pageSize", pageSize);
	List<Blog> allblog=blogService.getAllBlog();//��ȡ���в���
	List<Blog> blogByPage=blogService.listBlog(map);//����Map��ȡ��Ӧҳ���ò�������
	for(Blog blog:blogByPage){
		try {
			blog.setReleaseDateStr(DateUtil.formatString2(blog.getReleaseDate().toString()));//ת�����ݿ�����
		} catch (Exception e) {
			System.out.println("����ת��ʧ�ܣ�");
			e.printStackTrace();
		}
	}

	int blogTypeTotal=blogTypeService.getBlogTypeData().size();//�����������
	int linkTotal=linkService.getLinkData().size();//��ȡ��ǩ����
	int alltotals=blogTypeTotal+linkTotal;//��ǩ�������ڷ������ӵñ�ǩ
	int allBlogs=blogService.getAllBlog().size();//���еò���
	long allCategories=blogTypeService.getTotal();//�����������
	application.setAttribute("countsallblogs", allBlogs);//����Ӧ�ô�ŵ�ServletContext�У�ǰ��Ϊ������Ϣ�е���־��
	application.setAttribute("countsallcategories", allCategories);
	application.setAttribute("countsalltags", alltotals);
	application.setAttribute("blogList", blogByPage);
	application.setAttribute("firstnowPage", 1);
	application.setAttribute("firstblogcount", blogcounts);
	application.setAttribute("firsttotalPage", totalPage);
	Blogger blogger=bloggerService.getBloggerData();
	blogger.setPassword("");
	application.setAttribute("blogger", blogger);
	List<Link> linkList=linkService.getLinkData();
	application.setAttribute("linkList", linkList);
	List<BlogType> blogTypeList=blogTypeService.getBlogTypeData();
	application.setAttribute("blogTypeList", blogTypeList);
	}
//	PageBean pageBean=new PageBean(1, 10);//��һҳ�Լ�10����¼
//	Map<String, Object> map=new HashMap<String, Object>();
//	map.put("start", pageBean.getPage());
//	map.put("pageSize", pageBean.getPageSize());
//	List<Blog> blogList=blogService.listBlog(map);
//	try {
//		for(int i=0;i<blogList.size();i++)
//		{
//			List<String> list=new ArrayList<String>();
//			blogList.get(i).setReleaseDateStr(DateUtil.formatString(blogList.get(i).getReleaseDate().toString()));//����ʱ�� ת����ʽ
//			String imageString=blogList.get(i).getContent();//��ȡ��������
//			Document document=Jsoup.parse(imageString);//ͨ��Jsoup��������
//			Elements element=document.select("img[src$=.jpg|��png]");//��ȡ����ͼƬ��ַ
//			for(int j=0;j<element.size();j++){//ɾѡǰ����ͼƬ
//				if(j==3)
//					break;
//				list.add(element.get(j).attr("src"));		
//				
//			}
//			blogList.get(i).setImageList(list);
//		}
//	} catch (Exception e) {
//		e.printStackTrace();
//	}


}
