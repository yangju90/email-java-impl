package indi.mat.client.listener;

import java.awt.event.MouseEvent;

public class MyMouseListener extends java.awt.event.MouseAdapter {
	private static boolean flag = false;// �����ж��Ƿ��Ѿ�ִ��˫���¼�
	private static int clickNum = 0;// �����ж��Ƿ��ִ��˫���¼�

	public void mouseClicked(MouseEvent e) {
		final MouseEvent me = e;// �¼�Դ

		this.flag = false;// ÿ�ε������ʼ��˫���¼�ִ�б�־Ϊfalse

		if (this.clickNum == 1) {// ��clickNum==1ʱִ��˫���¼�
			this.mouseDoubleClicked(me);// ִ��˫���¼�
			this.clickNum = 0;// ��ʼ��˫���¼�ִ�б�־Ϊ0
			this.flag = true;// ˫���¼���ִ��,�¼���־Ϊtrue
			return;
		}

		// ���嶨ʱ��
		java.util.Timer timer = new java.util.Timer();

		// ��ʱ����ʼִ��,��ʱ0.2���ȷ���Ƿ�ִ�е����¼�
		timer.schedule(new java.util.TimerTask() {
			private int n = 0;// ��¼��ʱ��ִ�д���

			public void run() {
				if (MyMouseListener.flag) {// ���˫���¼��Ѿ�ִ��,��ôֱ��ȡ������ִ��
					n = 0;
					MyMouseListener.clickNum = 0;
					this.cancel();
					return;
				}
				if (n == 1) {// ��ʱ���ȴ�0.2���,˫���¼���δ����,ִ�е����¼�
					mouseSingleClicked(me);// ִ�е����¼�
					MyMouseListener.flag = true;
					MyMouseListener.clickNum = 0;
					n = 0;
					this.cancel();
					return;
				}
				clickNum++;
				n++;
			}
		}, new java.util.Date(), 500);
	}

	/**
	 * ��굥���¼�
	 * 
	 * @param e
	 *            �¼�Դ����
	 */
	public void mouseSingleClicked(MouseEvent e) {
		System.out.println("Single Clicked!");
	}

	/**
	 * ���˫���¼�
	 * 
	 * @param e
	 *            �¼�Դ����
	 */
	public void mouseDoubleClicked(MouseEvent e) {
		System.out.println("Doublc Clicked!");
	}
}