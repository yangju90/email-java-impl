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
 * ��������ʾ�ʼ�
 * ��������ʾһ���ʼ����ǰѷ�װ��Message�����е����ݽ��������������ʼ�ͷ�е��ʼ������ߵ�ַ���ʼ����塢����ʱ�䣬�ʼ������е��ı���Ϣ����Ƕ��Դ
 * ���ʼ����еĸ�����Ϣ�ȣ�Ȼ��ѽ�����������Щ��Ϣ����������ʾ�����ʾ�� JavaMail�����ʼ����������£�
 * (1).����Message�����getForm
 * ,getSubject�ȷ��������Եõ��ʼ������˺��������Ϣ������getContentType�����õ��ʼ������͡�
 * (2).ͨ��Message.getContentType�����ķ���ֵ�ж��ʼ�����
 * ��������Message.getContent�����õ��ʼ����ݡ�����ʼ�����Ϊ
 * ��text/plain����text/html������ʾ�ʼ�Ϊ���ı�����ʱ����Message�����getContent�����õ��ʼ�����
 * ��Ȼ�󽫷��ض��������ת����String�������ʾ������ɡ�����ʼ�����Ϊ��multipart/*������ʾ�ʼ�������һ���������ͣ���ʱ�轫Message.
 * getContent�������صĶ���ת����Multipart�� (3).
 * ����Multipart�����getCount�������Multipart�����з�װ�˶��ٸ�BodyPart����
 * ����ͨ��forѭ����һȡ��Multipart�����е�ÿ��BodyPart������д��� (4).
 * �ڴ���ÿ��BodyPart����ʱ�����ȵ���BodyPart�����getContentType�����õ�����MIME����
 * ��Ȼ�����MIME��������������������Ĵ��� a.��MIME���ͱ�ʾ����ͼƬ�������򸽼��ȶ���������ʱ��
 * ��ʱӦ����BodyPart�����getDataHandler�����õ���װ�����ݵ�DataHandler����
 * ��Ȼ�����DataHandler�����getInputStream���������������ص�InputStream����
 * ��ͨ�����InputStream�����м��ɻ��ԭʼ�Ķ������������ݡ�
 * b.��MIME����Ϊ��text/*��ʱ����ʾBodyPart�����б�����Ǵ��ı����ݣ�
 * ��ʱ����BodyPart�����getContent�����������صĶ���ת����String�������ʾ�����ʾ���ɡ�
 * c.��MIME�����ǡ�multipart/mixed��ʱ����ʾBodyPart�����б������һ������MIME��Ϣ��
 * ��ʱ����BodyPart�����е�getContent�����õ���װ����MIME��Ϣ�Ķ��󲢽���ת����Multipart���͡�
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
				// System.out.println("��" + i + "�������ǣ�" + mbp.getContentType());
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
