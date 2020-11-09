package demo;

import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import api.APIclient;
import api.ProductAPI;
import entities.Product;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class JFrameMain extends JFrame {

	private JPanel contentPane;
	private JTable tableProduct;
	private JTextField textFieldId;
	private JTextField textFieldName;
	private JTextField textFieldPrice;
	private JButton btnCreate;
	private JButton btnUpdate;
	private JButton btnDelete;
	private JTextArea textAreaDescription;
	private JScrollPane scrollPane;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				try
				{
					JFrameMain frame=new JFrameMain();
					frame.setVisible(true);
				} catch(Exception e)
				{
					e.printStackTrace();
				}				
			}
		});
	}
	
	public JFrameMain()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100,100,600,551);
		setTitle("Product Crud Operation");
		contentPane=new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(40, 27, 452, 146);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		contentPane.add(scrollPane);
		
		tableProduct = new JTable();
		tableProduct.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int selectedRow=tableProduct.getSelectedRow();
				String id=tableProduct.getValueAt(selectedRow, 0).toString();
				System.out.println(id);
				ProductAPI productAPI=APIclient.getClient().create(ProductAPI.class);
				productAPI.find(id).enqueue(new Callback<Product>() {

					@Override
					public void onFailure(Call<Product> arg0, Throwable arg1) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void onResponse(Call<Product> arg0, Response<Product> response) {
						if(response.isSuccessful())
						{
							Product product=response.body();
							textFieldId.setText(String.valueOf(product.getId()));
							textFieldName.setText(product.getName());
							textFieldPrice.setText(String.valueOf(product.getPrice()));
							textAreaDescription.setText(product.getDescription());
							//System.out.println(product.getName());
							
						}
						
					}
				});
			}
		});
		scrollPane.setRowHeaderView(tableProduct);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Product Info", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(41, 214, 464, 277);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblId = new JLabel("ID ");
		lblId.setBounds(12, 32, 34, 16);
		panel.add(lblId);
		
		textFieldId = new JTextField();
		textFieldId.setBounds(129, 29, 255, 22);
		panel.add(textFieldId);
		textFieldId.setColumns(10);
		
		textFieldName = new JTextField();
		textFieldName.setColumns(10);
		textFieldName.setBounds(129, 64, 255, 22);
		panel.add(textFieldName);
		
		JLabel lblPrice = new JLabel("PRICE");
		lblPrice.setBounds(12, 102, 34, 16);
		panel.add(lblPrice);
		
		textFieldPrice = new JTextField();
		textFieldPrice.setColumns(10);
		textFieldPrice.setBounds(129, 99, 255, 22);
		panel.add(textFieldPrice);
		
		JLabel lblDescription = new JLabel("DESCRIPTION");
		lblDescription.setBounds(12, 137, 97, 16);
		panel.add(lblDescription);
		
		JLabel lblName = new JLabel("NAME");
		lblName.setBounds(12, 67, 34, 16);
		panel.add(lblName);
		
		btnCreate = new JButton("CREATE");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					Product product=new Product();
					product.setId(Integer.parseInt(textFieldId.getText()));
					product.setName(textFieldName.getText());
					product.setPrice(Double.parseDouble(textFieldPrice.getText()));
					product.setDescription(textAreaDescription.getText());
					
					ProductAPI productAPI=APIclient.getClient().create(ProductAPI.class);
					productAPI.create(product).enqueue(new Callback<Void>() {

						@Override
						public void onFailure(Call<Void> arg0, Throwable t) {
							JOptionPane.showMessageDialog(null, t.getMessage());
						}

						@Override
						public void onResponse(Call<Void> arg0, Response<Void> response) {
							if(response.isSuccessful())
							{
								loadData();
							}							
						}
					});
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, e2.getMessage());
				}
				
			}
		});
		btnCreate.setBounds(26, 226, 97, 25);
		panel.add(btnCreate);
		
		btnUpdate = new JButton("UPDATE");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Product product=new Product();
				product.setId(Integer.parseInt(textFieldId.getText()));
				product.setName(textFieldName.getText());
				product.setPrice(Double.parseDouble(textFieldPrice.getText()));
				product.setDescription(textAreaDescription.getText());
				
				ProductAPI productAPI=APIclient.getClient().create(ProductAPI.class);
				productAPI.update(product).enqueue(new Callback<Void>() {
					
					@Override
					public void onResponse(Call<Void> arg0, Response<Void> response) {
						if(response.isSuccessful())
						{
							loadData();
						}
					}					
					@Override
					public void onFailure(Call<Void> arg0, Throwable arg1) {
						JOptionPane.showMessageDialog(null, arg1.getMessage());

						
					}
				});
			}
		});
		btnUpdate.setBounds(153, 226, 97, 25);
		panel.add(btnUpdate);
		
		btnDelete = new JButton("DELETE");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result=JOptionPane.showConfirmDialog(null, "Confirm","Are You Sure ",JOptionPane.YES_NO_OPTION);
				if(result==JOptionPane.YES_OPTION)
				{
					ProductAPI productAPI=APIclient.getClient().create(ProductAPI.class);
					productAPI.delete(textFieldId.getText()).enqueue(new Callback<Void>() {
						
						@Override
						public void onResponse(Call<Void> arg0, Response<Void> response) {
							if(response.isSuccessful())
							{
								loadData();
							}
						}
						
						@Override
						public void onFailure(Call<Void> arg0, Throwable t) {
							JOptionPane.showConfirmDialog(null, t.getMessage());

						}
					});

				}
			}
		});
		btnDelete.setBounds(287, 226, 97, 25);
		panel.add(btnDelete);
		
		textAreaDescription = new JTextArea();
		textAreaDescription.setBounds(129, 134, 255, 67);
		panel.add(textAreaDescription);
		loadData();
	}	
	private void loadData()
	{
		try {
			ProductAPI productAPI=APIclient.getClient().create(ProductAPI.class);
			productAPI.findAll().enqueue(new Callback<List<Product>>() {
				
				@Override
				public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
					if(response.isSuccessful())
					{
						//System.out.println(response.body());
						DefaultTableModel defaultTableModel=new DefaultTableModel();
						defaultTableModel.addColumn("Id");
						defaultTableModel.addColumn("Name");
						defaultTableModel.addColumn("Price");
						defaultTableModel.addColumn("Description");
						for(Product p:response.body())
						{
							defaultTableModel.addRow(new Object[] {
									p.getId(),p.getName(),
									p.getPrice(),p.getDescription()
							});
							//System.out.println(p.getId()+" "+p.getName()+" "+p.getPrice()+" "+p.getDescription());
						}						
						tableProduct.setModel(defaultTableModel);
					}					
				}				
				@Override
				public void onFailure(Call<List<Product>> arg0, Throwable t) {
					JOptionPane.showConfirmDialog(null,t.getMessage());					
				}
			});
		} catch (Exception e) {
			JOptionPane.showConfirmDialog(null, e.getMessage());
		}
	}
	
	
}
