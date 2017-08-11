package exp.sf.am.core;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI.NormalColor;

import exp.libs.utils.StrUtils;
import exp.libs.warp.ui.BeautyEyeUtils;
import exp.libs.warp.ui.SwingUtils;
import exp.libs.warp.ui.cpt.win.PopChildWindow;
import exp.libs.warp.ui.layout.VFlowLayout;
import exp.sf.am.bean.TAccount;

class _AccountWin extends PopChildWindow {
	
	/** serialVersionUID */
	private static final long serialVersionUID = 8372296143631208035L;

	private JTextField appName;

    private JTextField url;

    private JTextField loginUsername;

    private JTextField loginPassword;

    private JTextField queryPassword;

    private JTextField atmPassword;

    private JTextField payPassword;

    private JTextField servicePassword;

    private JTextField email;

    private JTextField phone;

    private JTextField idcardNum;

    private JTextField idcardName;

    private JTextField question1;

    private JTextField answer1;

    private JTextField question2;

    private JTextField answer2;

    private JTextField question3;

    private JTextField answer3;

    private JTextArea remark;
	
	private JButton okBtn;
	
	private JButton offBtn;
	
	private TAccount account;
	
	private boolean edit;
	
	private boolean edited;
	
	protected _AccountWin(TAccount account, Boolean edit) {
		super((edit ? "编辑帐密" : "查看详情"), 600, 580, false, account, edit);
	}
	
	@Override
	protected void initComponents(Object... args) {
		this.account = (TAccount) args[0];
		this.edit = (Boolean) args[1];
		initAccountField(edit);
		this.edited = false;
	}
	
	private void initAccountField(boolean edit) {
		this.appName = new JTextField(); { appName.setEditable(edit); }
		this.url = new JTextField(); { url.setEditable(edit); }
		this.loginUsername = new JTextField(); { loginUsername.setEditable(edit); }
		this.loginPassword = new JTextField(); { loginPassword.setEditable(edit); }
		this.queryPassword = new JTextField(); { queryPassword.setEditable(edit); }
		this.atmPassword = new JTextField(); { atmPassword.setEditable(edit); }
		this.payPassword = new JTextField(); { payPassword.setEditable(edit); }
		this.servicePassword = new JTextField(); { servicePassword.setEditable(edit); }
		this.email = new JTextField(); { email.setEditable(edit); }
		this.phone = new JTextField(); { phone.setEditable(edit); }
		this.idcardNum = new JTextField(); { idcardNum.setEditable(edit); }
		this.idcardName = new JTextField(); { idcardName.setEditable(edit); }
		this.question1 = new JTextField(); { question1.setEditable(edit); }
		this.answer1 = new JTextField(); { answer1.setEditable(edit); }
		this.question2 = new JTextField(); { question2.setEditable(edit); }
		this.answer2 = new JTextField(); { answer2.setEditable(edit); }
		this.question3 = new JTextField(); { question3.setEditable(edit); }
		this.answer3 = new JTextField(); { answer3.setEditable(edit); }
		this.remark = new JTextArea(5, 8); { remark.setEditable(edit); }
		
		this.okBtn = new JButton("确认"); { okBtn.setEnabled(edit); }
		this.offBtn = new JButton("关闭");
		BeautyEyeUtils.setButtonStyle(NormalColor.lightBlue, okBtn, offBtn);
	}

	private boolean getValueFromUI() {
		boolean isOk = StrUtils.isNotTrimEmpty(appName.getText());
		if(isOk == true) {
			account.encodeAppName(appName.getText());
			account.encodeUrl(url.getText());
			account.encodeLoginUsername(loginUsername.getText());
			account.encodeLoginPassword(loginPassword.getText());
			account.encodeQueryPassword(queryPassword.getText());
			account.encodeAtmPassword(atmPassword.getText());
			account.encodePayPassword(payPassword.getText());
			account.encodeServicePassword(servicePassword.getText());
			account.encodeEmail(email.getText());
			account.encodePhone(phone.getText());
			account.encodeIdcardNum(idcardNum.getText());
			account.encodeIdcardNum(idcardNum.getText());
			account.encodeQuestion1(question1.getText());
			account.encodeAnswer1(answer1.getText());
			account.encodeQuestion2(question2.getText());
			account.encodeAnswer2(answer2.getText());
			account.encodeQuestion3(question3.getText());
			account.encodeAnswer3(answer3.getText());
			account.encodeRemark(remark.getText());
		}
		return isOk;
	}
	
	@Override
	protected void setComponentsLayout(JPanel rootPanel) {
		rootPanel.add(toEditPanel(), BorderLayout.CENTER);
	}
	
	private JPanel toEditPanel() {
		JPanel panel = new JPanel(new VFlowLayout()); {
			panel.add(new JLabel());
			panel.add(getWEBorderPanel("应用名称", appName));
			panel.add(new JLabel());
			panel.add(getWEBorderPanel("相关网址", url));
			panel.add(new JLabel());
			panel.add(SwingUtils.getHGridPanel(
					getWEBorderPanel("登陆账号", loginUsername), 
					getWEBorderPanel("登陆密码", loginPassword)));
			panel.add(new JLabel());
			panel.add(SwingUtils.getHGridPanel(
					getWEBorderPanel("查询密码", queryPassword), 
					getWEBorderPanel("取款密码", atmPassword)));
			panel.add(new JLabel());
			panel.add(SwingUtils.getHGridPanel(
					getWEBorderPanel("支付密码", payPassword), 
					getWEBorderPanel("服务密码", servicePassword)));
			panel.add(new JLabel());
			panel.add(SwingUtils.getHGridPanel(
					getWEBorderPanel("绑定邮箱", email), 
					getWEBorderPanel("绑定手机", phone)));
			panel.add(new JLabel());
			panel.add(SwingUtils.getHGridPanel(
					getWEBorderPanel("身份证号", idcardNum), 
					getWEBorderPanel("身份证名", idcardName)));
			panel.add(new JLabel());
			panel.add(SwingUtils.getHGridPanel(
					getWEBorderPanel("密码提示问题1", question1), 
					getWEBorderPanel("密码提示答案1", answer1)));
			panel.add(new JLabel());
			panel.add(SwingUtils.getHGridPanel(
					getWEBorderPanel("密码提示问题2", question2), 
					getWEBorderPanel("密码提示答案2", answer2)));
			panel.add(new JLabel());
			panel.add(SwingUtils.getHGridPanel(
					getWEBorderPanel("密码提示问题3", question3), 
					getWEBorderPanel("密码提示答案3", answer3)));
			panel.add(new JLabel());
			panel.add(getWEBorderPanel("备注", SwingUtils.addScroll(remark)));
			panel.add(new JLabel());
			panel.add(toBtnPanel());
			panel.add(new JLabel());
		} SwingUtils.addBorder(panel);
		return panel;
	}
	
	private JPanel getWEBorderPanel(String key, Component val) {
		return SwingUtils.getWEBorderPanel(
				new JLabel("  [".concat(key).concat("] :  ")), 
				val, new JLabel("   "));
	}

	private JPanel toBtnPanel() {
		JPanel panel = new JPanel(new GridLayout(1, 2)); {
			panel.add(SwingUtils.getEBorderPanel(new JLabel(), 
					SwingUtils.getWBorderPanel(new JLabel("   "), okBtn)), 0);
			panel.add(SwingUtils.getWBorderPanel(new JLabel(), 
					SwingUtils.getEBorderPanel(new JLabel("   "), offBtn)), 1);
		}
		return panel;
	}
	
	@Override
	protected void setComponentsListener(JPanel rootPanel) {
		okBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(getValueFromUI()) {
					edited = DBMgr.edit(account);
					SwingUtils.info("保存".concat(edited ? "成功" : "失败"));
					
				} else {
					SwingUtils.info("[应用名称] 不能为空");
				}
			}
		});
		
		offBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				_hide();
			}
		});
	}
	
	protected boolean isEdited() {
		return edited;
	}

}