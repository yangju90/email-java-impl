# email-java-impl
本项目主要利用了javax.mail.jar完成了一个简单的邮件收发功能。

#### 1. JavaMail文件夹为一个单独的工程，里面包括了收发邮件的例子，和一个丑到爆的桌面软件。    
    
* indi.mat.client.*中的内容一律为界面程序；  
* preference中放的是javamail-1.5.6-src.zip 源码包；  
* lib中放的是邮件开发的jar包。 
   
#### 2. JavaMailClient文件夹是一个Swing编写的桌面程序，完成了邮件的接收，没有发送功能。  
    
* 程序运行default中的Main.java；  
* 填写用户名密码，仅支持163邮件的接收，需开通邮箱POP3功能，邮件解析显示仅是简单的使用了JEditorPane，解析并不完善；  
* 因为毕业设计的原因，其他功能的完善暂不完成，有机会继续。 
  
