package giaodien.NhanVien;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import control.ControlGiaoDien;
import control.DanhSachDuLieu;
import entity.KhachHang;
import entity.ThongTinThuoc;
import giaodien.GiaoDienDangNhap;
import giaodien.GiaoDienThongTinNhanVien;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Font;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;
import java.awt.Component;

public class GiaoDienLapHoaDon extends JFrame {

	private JFrame frmLapHoaDon;
	private JTable table,tableThemThuoc;
	public DefaultTableModel tablemode,	tablemodelBangThemThuoc;
	private JScrollPane scrollPane_1;
	private JTextField txtTongTien;
	private JPanel panelDienThongTin, panelBangThemThuoc;
	private JComboBox cbbNgay,cbbThang,cbbNam;
	GiaoDienDangNhap dn;
	ControlGiaoDien cc;
	DanhSachDuLieu ds = new DanhSachDuLieu();
	private JTextArea txtAMota;
	private JTextField txtNguoiLap;
	private JTextField txtMa;
	private JTextField txtNgay;
	private JTextField txtCMND;
	private JTextField txtTenKH;
	private JTextField txtSDT;
	private JTextField txtTenThuoc_TimKiem;
	private JTextField txtSoLuong;
	private JTextField txtLoai;
	private JTextField txtSoLuong_BangThemThuoc;
	private JScrollPane scrollPaneBangThemThuoc_1;
	public GiaoDienLapHoaDon() {
		setResizable(false);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setTitle("Lập hóa đơn");
		setBounds(500, 100, 490, 600);
		getContentPane().setLayout(null);

		String[] ngay={"01", "02", "03", "04", "05", "06", "07", "08", "09",
				"10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23",
				"24", "25", "26", "27", "28", "29", "30", "31"};

		String[] thang ={"01", "02", "04", "05", "06", "07", "08", "09", "10", "11", "12"};


		String[] headerBangThemThuoc="Tên thuốc;Số lượng;Đơn vị tính;Đơn giá".split(";");
		tablemodelBangThemThuoc = new DefaultTableModel(headerBangThemThuoc,0);


		String [] header="Tên thuốc;Số lượng;Đơn vị tính;Đơn giá;Thành tiền".split(";");
		JScrollPane scrollPane = new JScrollPane();
		tablemode =new DefaultTableModel(header, 0);
		getContentPane().add(scrollPane_1 = new JScrollPane(table =new JTable(tablemode)));
		scrollPane_1.setSize(464, 194);
		scrollPane_1.setLocation(10, 290);

		JLabel lblTongTien = new JLabel("Tổng tiền:");
		lblTongTien.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTongTien.setBounds(243, 490, 72, 20);
		getContentPane().add(lblTongTien);

		txtTongTien = new JTextField();
		txtTongTien.setEditable(false);
		txtTongTien.setEnabled(false);
		txtTongTien.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtTongTien.setBounds(326, 490, 137, 22);
		txtTongTien.setBorder(null);
		getContentPane().add(txtTongTien);
		txtTongTien.setColumns(10);

		JButton btnXoq = new JButton("Xong");
		btnXoq.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnXoq.setHorizontalAlignment(SwingConstants.LEFT);
		btnXoq.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnXoq.setIcon(new ImageIcon(GiaoDienLapHoaDon.class.getResource("/ser/done.png")));
		btnXoq.setBounds(188, 523, 117, 34);
		getContentPane().add(btnXoq);

		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, 484, 279);
		getContentPane().add(layeredPane);
		
				panelDienThongTin = new JPanel();
				panelDienThongTin.setToolTipText("Thông tin");
				panelDienThongTin.setBounds(0, 0, 484, 279);
				layeredPane.add(panelDienThongTin);
				panelDienThongTin.setLayout(null);
				
						JLabel label = new JLabel("THÔNG TIN HÓA ĐƠN");
						label.setForeground(new Color(75, 0, 130));
						label.setFont(new Font("Times New Roman", Font.BOLD, 22));
						label.setBounds(111, 11, 258, 34);
						panelDienThongTin.add(label);
						
								JCheckBox chkKeDon = new JCheckBox("Có kê đơn");
								
										chkKeDon.setToolTipText("Chọn nếu bán có kê đơn");
										chkKeDon.setFont(new Font("Tahoma", Font.BOLD, 11));
										chkKeDon.setBounds(24, 100, 94, 23);
										panelDienThongTin.add(chkKeDon);
										
												JLabel lblMHan = new JLabel("Mã hóa đơn:");
												lblMHan.setFont(new Font("Tahoma", Font.BOLD, 11));
												lblMHan.setBounds(21, 67, 78, 17);
												panelDienThongTin.add(lblMHan);
												
														JLabel lblNgyLp = new JLabel("Ngày lập:");
														lblNgyLp.setFont(new Font("Tahoma", Font.BOLD, 11));
														lblNgyLp.setBounds(164, 67, 59, 17);
														panelDienThongTin.add(lblNgyLp);
														
																JLabel label_3 = new JLabel("Người lập:");
																label_3.setFont(new Font("Tahoma", Font.BOLD, 11));
																label_3.setBounds(301, 67, 65, 17);
																panelDienThongTin.add(label_3);
																
																		txtNguoiLap = new JTextField();
																		txtNguoiLap.setText((String) null);
																		txtNguoiLap.setEnabled(false);
																		txtNguoiLap.setEditable(false);
																		txtNguoiLap.setColumns(10);
																		txtNguoiLap.setBounds(362, 65, 55, 20);
																		panelDienThongTin.add(txtNguoiLap);
																		
																				txtMa = new JTextField();
																				txtMa.setEnabled(false);
																				txtMa.setEditable(false);
																				txtMa.setColumns(10);
																				txtMa.setBounds(99, 65, 55, 20);
																				panelDienThongTin.add(txtMa);
																				
																						txtNgay = new JTextField();
																						txtNgay.setEnabled(false);
																						txtNgay.setEditable(false);
																						txtNgay.setColumns(10);
																						txtNgay.setBounds(219, 65, 72, 20);
																						panelDienThongTin.add(txtNgay);
																						
																								JButton btnThongTinNhanVien = new JButton("");
																								btnThongTinNhanVien.setIcon(new ImageIcon(GiaoDienLapHoaDon.class.getResource("/ser/user.png")));
																								btnThongTinNhanVien.addActionListener(new ActionListener() {
																									public void actionPerformed(ActionEvent arg0) {
																										try {
																											new GiaoDienThongTinNhanVien().setVisible(true);
																										} catch (SQLException e) {
																											// TODO Auto-generated catch block
																											e.printStackTrace();
																										}
																									}
																								});
																								btnThongTinNhanVien.setBounds(417, 60, 33, 29);
																								panelDienThongTin.add(btnThongTinNhanVien);
																								
																										JPanel panelThongtinKH = new JPanel();
																										panelThongtinKH.setLayout(null);
																										panelThongtinKH.setForeground(Color.RED);
																										panelThongtinKH.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Th\u00F4ng tin kh\u00E1ch h\u00E0ng:", TitledBorder.TRAILING, TitledBorder.TOP, null, new Color(0, 128, 0)));
																										panelThongtinKH.setBounds(10, 104, 453, 133);
																										panelDienThongTin.add(panelThongtinKH);
																										
																												JLabel label_4 = new JLabel("CMND: ");
																												label_4.setEnabled(false);
																												label_4.setBounds(10, 26, 67, 14);
																												panelThongtinKH.add(label_4);
																												
																														JLabel label_5 = new JLabel("Tên KH:");
																														label_5.setEnabled(false);
																														label_5.setBounds(10, 51, 67, 14);
																														panelThongtinKH.add(label_5);
																														
																																JLabel label_6 = new JLabel("Ngày sinh:");
																																label_6.setEnabled(false);
																																label_6.setBounds(10, 76, 67, 14);
																																panelThongtinKH.add(label_6);
																																
																																		JLabel label_7 = new JLabel("SDT:");
																																		label_7.setEnabled(false);
																																		label_7.setBounds(10, 101, 67, 14);
																																		panelThongtinKH.add(label_7);
																																		
																																				txtCMND = new JTextField();
																																				txtCMND.setEditable(false);
																																				txtCMND.setColumns(10);
																																				txtCMND.setBounds(87, 26, 143, 20);
																																				panelThongtinKH.add(txtCMND);
																																				
																																						txtTenKH = new JTextField();
																																						txtTenKH.setEditable(false);
																																						txtTenKH.setColumns(10);
																																						txtTenKH.setBounds(87, 51, 167, 20);
																																						panelThongtinKH.add(txtTenKH);
																																						
																																								txtSDT = new JTextField();
																																								txtSDT.setEditable(false);
																																								txtSDT.setColumns(10);
																																								txtSDT.setBounds(87, 101, 167, 20);
																																								panelThongtinKH.add(txtSDT);
																																								
																																										JButton btnKiemTra = new JButton("");
																																										btnKiemTra.addActionListener(new ActionListener() {
																																											public void actionPerformed(ActionEvent e) {
																																												String cmnd;
																																												boolean kt=false;
																																												cmnd = txtCMND.getText()+"";
																																												try {
																																													ds.docBangKhachHang();
																																												} catch (SQLException e1) {
																																													// TODO Auto-generated catch block
																																													e1.printStackTrace();
																																												}
																																												for(KhachHang kh : ds.listKhachHang)
																																												{
																																													if(kh.getCMND().equalsIgnoreCase(cmnd))
																																													{
																																														String[] date=kh.getNgaySinh().split("-");
																																														txtTenKH.setText(kh.getHoTenKH()+"");
																																														txtSDT.setText(kh.getSdt()+"");
																																														txtAMota.setText(kh.getMoTaKH()+"");
																																														cbbThang.setSelectedItem(date[1]);
																																														cbbNgay.setSelectedItem(date[2]);
																																														cbbNam.setSelectedItem(date[0]);
																																														kt= true;
																																													}
																																												}
																																												if (kt==false)
																																													JOptionPane.showMessageDialog(panelThongtinKH, "Khách hàng chưa có, vui lòng thêm mới !!");
																																											}
																																										});
																																										btnKiemTra.setIcon(new ImageIcon(GiaoDienLapHoaDon.class.getResource("/ser/search.png")));
																																										btnKiemTra.setToolTipText("Kiểm tra");
																																										btnKiemTra.setEnabled(false);
																																										btnKiemTra.setBounds(231, 26, 23, 21);
																																										panelThongtinKH.add(btnKiemTra);
																																										
																																												txtAMota = new JTextArea();
																																												txtAMota.setEditable(false);
																																												txtAMota.setBackground(Color.WHITE);
																																												txtAMota.setBounds(264, 46, 179, 75);
																																												panelThongtinKH.add(txtAMota);
																																												
																																														JLabel label_8 = new JLabel("Mô tả:");
																																														label_8.setEnabled(false);
																																														label_8.setBounds(264, 26, 46, 14);
																																														panelThongtinKH.add(label_8);
																																														
																																																cbbNgay = new JComboBox(new Object[]{});
																																																cbbNgay.setEnabled(false);
																																																cbbNgay.setEditable(false);
																																																cbbNgay.setBounds(87, 76, 46, 20);
																																																panelThongtinKH.add(cbbNgay);
																																																
																																																		cbbThang = new JComboBox(new Object[]{});
																																																		cbbThang.setEnabled(false);
																																																		cbbThang.setEditable(false);
																																																		cbbThang.setBounds(143, 76, 46, 20);
																																																		panelThongtinKH.add(cbbThang);
																																																		
																																																				cbbNam = new JComboBox();
																																																				cbbNam.setEnabled(false);
																																																				cbbNam.setEditable(false);
																																																				cbbNam.setBounds(199, 76, 55, 20);
																																																				panelThongtinKH.add(cbbNam);
																																																				
																																																						JLabel label_9 = new JLabel("Tên thuốc:");
																																																						label_9.setFont(new Font("Tahoma", Font.BOLD, 11));
																																																						label_9.setBounds(10, 248, 65, 22);
																																																						panelDienThongTin.add(label_9);
																																																						
																																																								JLabel label_10 = new JLabel("Số lượng:");
																																																								label_10.setFont(new Font("Tahoma", Font.BOLD, 11));
																																																								label_10.setBounds(250, 248, 55, 22);
																																																								panelDienThongTin.add(label_10);
																																																								
																																																										txtTenThuoc_TimKiem = new JTextField();
																																																										txtTenThuoc_TimKiem.setColumns(10);
																																																										txtTenThuoc_TimKiem.setBounds(75, 249, 142, 21);
																																																										panelDienThongTin.add(txtTenThuoc_TimKiem);
																																																										
																																																												txtSoLuong = new JTextField();
																																																												txtSoLuong.setToolTipText("Vui lòng nhập số: ");
																																																												txtSoLuong.setColumns(10);
																																																												txtSoLuong.setBounds(315, 250, 48, 20);
																																																												panelDienThongTin.add(txtSoLuong);
																																																												
																																																														JButton btnXoa = new JButton("");
																																																														btnXoa.setIcon(new ImageIcon(GiaoDienLapHoaDon.class.getResource("/ser/cancel.png")));
																																																														btnXoa.addActionListener(new ActionListener() {
																																																															public void actionPerformed(ActionEvent arg0) {
																																																																int row = table.getSelectedRow();
																																																																if (row==-1)
																																																																	JOptionPane.showMessageDialog(panelDienThongTin, "Vui lòng chọn thuốc");
																																																																else {
																																																																	int sel = JOptionPane.showConfirmDialog(panelDienThongTin, "Bạn có muốn xóa không?","Xóa", JOptionPane.YES_NO_OPTION);
																																																																	if (sel == JOptionPane.YES_OPTION)
																																																																		tablemode.removeRow(row);
																																																																}
																																																															}
																																																														});
																																																														btnXoa.setToolTipText("Xóa");
																																																														btnXoa.setBounds(437, 248, 26, 23);
																																																														panelDienThongTin.add(btnXoa);
																																																														
																																																																JButton btnThemNangCao = new JButton("");
																																																																btnThemNangCao.setIcon(new ImageIcon(GiaoDienLapHoaDon.class.getResource("/ser/more_01.png")));
																																																																btnThemNangCao.addActionListener(new ActionListener() {
																																																																	public void actionPerformed(ActionEvent arg0) {
																																																																		panelBangThemThuoc.setVisible(true);
																																																																		panelDienThongTin.setVisible(false);
																																																																	}
																																																																});
																																																																btnThemNangCao.setToolTipText("Mở bảng");
																																																																btnThemNangCao.setBounds(217, 249, 26, 21);
																																																																panelDienThongTin.add(btnThemNangCao);
																																																																
																																																																		JButton btnThem = new JButton("");
																																																																		btnThem.setIcon(new ImageIcon(GiaoDienLapHoaDon.class.getResource("/ser/add.png")));
																																																																		btnThem.addActionListener(new ActionListener() {
																																																																			public void actionPerformed(ActionEvent arg0) {

																																																																				String ten = txtTenThuoc_TimKiem.getText()+"";
																																																																				int soLuong = Integer.parseInt(txtSoLuong.getText());
																																																																				try {
																																																																					ds.docThuoc();
																																																																				} catch (SQLException e) {
																																																																					// TODO Auto-generated catch block
																																																																					e.printStackTrace();
																																																																				}
																																																																				for(ThongTinThuoc thuoc : ds.listThuoc)
																																																																				{
																																																																					if(ten.equals(thuoc.getTenThuoc()+""))
																																																																					{
																																																																						Object[] row = {ten,soLuong,thuoc.getDonViTinh(),thuoc.getGiaBan()};
																																																																						tablemode.addRow(row);
																																																																					}
																																																																				}
																																																																				txtTongTien.setText(tongTien(tablemode)+"");

																																																																			}
																																																																		});
																																																																		btnThem.setToolTipText("Thêm");
																																																																		btnThem.setBounds(405, 248, 26, 23);
																																																																		panelDienThongTin.add(btnThem);
																																																																		
																																																																				txtLoai = new JTextField();
																																																																				txtLoai.setEnabled(false);
																																																																				txtLoai.setEditable(false);
																																																																				txtLoai.setColumns(10);
																																																																				txtLoai.setBounds(362, 250, 33, 20);
																																																																				panelDienThongTin.add(txtLoai);
																																																																				txtNguoiLap.setText(dn.txtTK.getText());
																																																																				
																																																																						chkKeDon.addActionListener(new ActionListener() {
																																																																				
																																																																							@Override
																																																																							public void actionPerformed(ActionEvent arg0) {
																																																																								// TODO Auto-generated method stub
																																																																								if(chkKeDon.isSelected())
																																																																								{
																																																																									panelThongtinKH.setEnabled(true);
																																																																									label_3.setEnabled(true);
																																																																									label_4.setEnabled(true);
																																																																									label_5.setEnabled(true);
																																																																									label_6.setEnabled(true);
																																																																									label_7.setEnabled(true);
																																																																									txtCMND.setEditable(true);
																																																																									cbbNgay.setEnabled(true);
																																																																									cbbNgay.setEditable(true);
																																																																									cbbThang.setEnabled(true);
																																																																									cbbThang.setEditable(true);
																																																																									cbbNam.setEnabled(true);
																																																																									cbbNam.setEditable(true);
																																																																									txtSDT.setEditable(true);
																																																																									txtTenKH.setEditable(true);
																																																																									txtAMota.setEditable(true);
																																																																									txtAMota.setBackground(Color.decode("#ffffff"));
																																																																									btnKiemTra.setEnabled(true);
																																																																								}
																																																																								else {
																																																																									panelThongtinKH.setEnabled(false);
																																																																									label_3.setEnabled(false);
																																																																									label_4.setEnabled(false);
																																																																									label_5.setEnabled(false);
																																																																									label_6.setEnabled(false);
																																																																									label_7.setEnabled(false);
																																																																									txtCMND.setEditable(false);
																																																																									cbbNgay.setEnabled(false);
																																																																									cbbNgay.setEditable(false);
																																																																									cbbThang.setEnabled(false);
																																																																									cbbThang.setEditable(false);
																																																																									cbbNam.setEnabled(false);
																																																																									cbbNam.setEditable(false);
																																																																									txtSDT.setEditable(false);
																																																																									txtTenKH.setEditable(false);
																																																																									txtAMota.setEditable(false);
																																																																									txtAMota.setBackground(Color.decode("#ffffff"));
																																																																									btnKiemTra.setEnabled(false);
																																																																								}
																																																																				
																																																																							}
																																																																						});
																																																																						txtMa.setText("HD"+ds.demSoHDBan());

		panelBangThemThuoc = new JPanel();
		panelBangThemThuoc.setForeground(Color.ORANGE);
		panelBangThemThuoc.setBounds(10, 11, 464, 268);
		layeredPane.add(panelBangThemThuoc);
		panelBangThemThuoc.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Ch\u1ECDn thu\u1ED1c t\u1EEB b\u1EA3ng \u0111\u1EC3 th\u00EAm v\u00E0o h\u00F3a \u0111\u01A1n:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 128, 0)));
		panelBangThemThuoc.setLayout(null);
		panelBangThemThuoc.add(scrollPaneBangThemThuoc_1 = new JScrollPane(tableThemThuoc = new JTable(tablemodelBangThemThuoc) ));
		scrollPaneBangThemThuoc_1.setBounds(10, 21, 370, 238);

		//		tableThemThuoc.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
		//			
		//			@Override
		//			public void valueChanged(ListSelectionEvent arg0) {
		//				// TODO Auto-generated method stub
		//				int row = tableThemThuoc.getSelectedRow();
		//			}
		//		});

		JButton btnThem_BangThemThuoc = new JButton("");
		btnThem_BangThemThuoc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int row = tableThemThuoc.getSelectedRow();

				if (row==-1)
					JOptionPane.showMessageDialog(panelBangThemThuoc, "Vui lòng chọn thuốc cần thêm !!!");
				else {
					if (txtSoLuong_BangThemThuoc.getText().equals("")) {
						JOptionPane.showMessageDialog(panelBangThemThuoc, "Vui lòng nhập số lượng !! ");
					}
					else {
						int sl = Integer.parseInt(txtSoLuong_BangThemThuoc.getText());
						if (sl<=0)
							JOptionPane.showMessageDialog(panelBangThemThuoc, "Số lượng không được âm và khác 0 !!");
						else {
								themThuocTuBangVaoHoaDon();
								txtTongTien.setText(tongTien(tablemode)+"");
							}
						}
				}
			}
		});
		btnThem_BangThemThuoc.setToolTipText("Thêm");
		btnThem_BangThemThuoc.setIcon(new ImageIcon(GiaoDienLapHoaDon.class.getResource("/ser/add1.png")));
		btnThem_BangThemThuoc.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnThem_BangThemThuoc.setBounds(390, 106, 64, 64);
		panelBangThemThuoc.add(btnThem_BangThemThuoc);

		JLabel lblSLng = new JLabel("Số lượng:");
		lblSLng.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSLng.setBounds(391, 36, 52, 14);
		panelBangThemThuoc.add(lblSLng);

		txtSoLuong_BangThemThuoc = new JTextField();
		txtSoLuong_BangThemThuoc.setToolTipText("Vui lòng nhập số");
		txtSoLuong_BangThemThuoc.setBounds(390, 61, 64, 20);
		txtSoLuong_BangThemThuoc.setColumns(10);
		panelBangThemThuoc.add(txtSoLuong_BangThemThuoc);

		JButton btnHuyBangThemThuoc = new JButton("");
		btnHuyBangThemThuoc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panelBangThemThuoc.setVisible(false);
				panelDienThongTin.setVisible(true);
			}
		});
		btnHuyBangThemThuoc.setToolTipText("Xong");
		btnHuyBangThemThuoc.setIcon(new ImageIcon(GiaoDienLapHoaDon.class.getResource("/ser/button_cancel.png")));
		btnHuyBangThemThuoc.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnHuyBangThemThuoc.setBounds(390, 193, 64, 64);
		panelBangThemThuoc.add(btnHuyBangThemThuoc);

		JScrollPane scrollPaneBangThemThuoc = new JScrollPane((Component) null);
		scrollPaneBangThemThuoc.setBounds(10, 36, 373, 223);

		//--------------------------------------
		layNgayHeThong();
		try {
			ds.docThuoc();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		for(ThongTinThuoc thuoc : ds.listThuoc)
		{
			Object[] row ={thuoc.getTenThuoc(),thuoc.getSoLuong(),thuoc.getDonViTinh(),thuoc.getGiaBan()};
			tablemodelBangThemThuoc.addRow(row);
		}

		//--------------------------------------------------

	}
	public void layNgayHeThong()
	{
		Date today=new Date(System.currentTimeMillis());
		SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy/MM/dd");
		//SimpleDateFormat timeFormat= new SimpleDateFormat(“hh:mm:ss dd/MM/yyyy”);
		String s=timeFormat.format(today.getTime());
		txtNgay.setText(s);
	}
	public void themThuocTuBangVaoHoaDon() {
		int row = tableThemThuoc.getSelectedRow();
		if (row!=-1) {
			String tenThuoc = tableThemThuoc.getValueAt(row, 0)+"";
			int soluong = Integer.parseInt(txtSoLuong_BangThemThuoc.getText());
			String dvt = tableThemThuoc.getValueAt(row, 2) +"";
			double dongia = (double) tableThemThuoc.getValueAt(row, 3);
			double tt = soluong*dongia;
			Object a[] = {tenThuoc,soluong,dvt,dongia,tt};
			tablemode.addRow(a);
		}
	}
	public double tongTien(DefaultTableModel tbm)
	{
		double tong = 0;
		for(int i=tbm.getRowCount()-1;i>=0;i--)
		{
			double tong2=Double.parseDouble(tbm.getValueAt(i, 4)+"");
			tong+=tong2;
		}
		return tong;
	}
}