package indi.mat.prase;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JEditorPane;

import indi.mat.client.main.ClientMain;

/**
 * 
 * 解析和显示邮件
 * 解析和显示一封邮件就是把封装在Message对象中的数据解析出来，包括邮件头中的邮件发送者地址、邮件主体、发送时间，邮件正文中的文本信息、内嵌资源
 * ，邮件体中的附件信息等，然后把解析出来的这些信息交给数据显示软件显示。 JavaMail解析邮件的流程如下：
 * (1).调用Message对象的getForm
 * ,getSubject等方法，可以得到邮件发送人和主题等信息，调用getContentType方法得到邮件的类型。
 * (2).通过Message.getContentType方法的返回值判断邮件类型
 * ，并调用Message.getContent方法得到邮件内容。如果邮件类型为
 * “text/plain”或”text/html”，表示邮件为纯文本，此时调用Message对象的getContent方法得到邮件内容
 * ，然后将返回对象的类型转换成String输出给显示软件即可。如果邮件类型为”multipart/*”，表示邮件内容是一个复合类型，此时需将Message.
 * getContent方法返回的对象转换成Multipart。 (3).
 * 调用Multipart对象的getCount方法检测Multipart对象中封装了多少个BodyPart对象
 * ，并通过for循环逐一取出Multipart对象中的每个BodyPart对象进行处理。 (4).
 * 在处理每个BodyPart对象时，首先调用BodyPart对象的getContentType方法得到他的MIME类型
 * ，然后根据MIME类型做出如下三种情况的处理： a.当MIME类型表示的是图片、声音或附件等二进制数据时，
 * 此时应调用BodyPart对象的getDataHandler方法得到封装了数据的DataHandler对象
 * ，然后调用DataHandler对象的getInputStream方法获得与数据相关的InputStream对象
 * ，通过这个InputStream对象中即可获得原始的二进制数据内容。
 * b.当MIME类型为”text/*”时，表示BodyPart对象中保存的是纯文本数据，
 * 此时调用BodyPart对象的getContent方法并将返回的对象转换成String输出给显示软键显示即可。
 * c.当MIME类型是”multipart/mixed”时，表示BodyPart对象中保存的是一个复合MIME消息，
 * 此时调用BodyPart对象中的getContent方法得到封装复合MIME消息的对象并将它转换成Multipart类型。
 *
 */

public class PraseContent {

	private MimeMessage mesg = null;
	private JEditorPane jep = null;

	public PraseContent(MimeMessage mesg,ClientMain cm) {
		this.mesg = mesg;
		praseContent(cm);
	}

	private void praseContent(ClientMain cm) {
		try {
			if ("text".equals(mesg.getContentType().substring(0, 4))) {
//				System.out.println(mesg.getContent());
//				System.out.println(mesg.getContentType().substring(0, 9));
				jep = cm.getJep();
				jep.setContentType("text/html");
				jep.setText((String) mesg.getContent());
			} else {
				MimeMultipart mp = (MimeMultipart) mesg.getContent();
				praseMulti(mp,cm);
			}
		} catch (MessagingException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void praseMulti(MimeMultipart mp,ClientMain cm) {
		try {
			int n = mp.getCount();
			for (int i = 0; i < n; i++) {
				MimeBodyPart mbp = (MimeBodyPart) mp.getBodyPart(i);
				// System.out.println("第" + i + "个类型是：" + mbp.getContentType());
				praseBody(mbp,cm);
			}
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void praseBody(MimeBodyPart mbp,ClientMain cm) {
		try {
			if ("mult".equals(mbp.getContentType().substring(0, 4))) {
				MimeMultipart mp = (MimeMultipart) mbp.getContent();
				praseMulti(mp,cm);
			} else if ("text".equals(mbp.getContentType().substring(0, 4))) {
//				cm.getJta().setText((String) mbp.getContent());
				jep = cm.getJep();
//				jep.setContentType(mbp.getContentType());
				jep.setContentType("text/html");
				jep.setText((String) mbp.getContent());
				
			} else {
				int i = mbp.getContentType().indexOf("=");
				String substr = mbp.getContentType().substring(i + 1);
//				System.out.println("==============");
//				System.out.println(substr);
//				System.out.println("==============");
				// InputStream is = mbp.getInputStream();
				InputStream is = mbp.getDataHandler().getInputStream();
				File file = new File("F:/" + substr);
				FileOutputStream os = new FileOutputStream(file);
				byte[] b = new byte[1024];
				int length = 0;
				while ((length = is.read(b)) > 0) {
					os.write(b,0,length);
				}
				os.flush();
				os.close();
			}

		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static PraseContent getInstance(MimeMessage mesg, ClientMain cm) {
		return new PraseContent(mesg,cm);
	}
}
