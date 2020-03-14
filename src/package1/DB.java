package package1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class DB {

	public static String url="jdbc:postgresql://127.0.0.1:5432/ScoreSheetEditor-PlayerInfo" ;
	public static String user = "postgres" ;
    public static String password = "0221" ;

    public static Connection con;
    public static Statement stmt;
    public static PreparedStatement prestmt;
    public static ResultSet result;

    static DefaultTableModel model;
    static JTable table;
    static JScrollPane sp;

    static String[] TableTitle= {"player","team"};
    static String[] KeyName= {"名前","チーム名"};

    String playerList;
    int playerKey,teamKey;

    //接続
    public static void connect() {

    	try {
    		con = DriverManager.getConnection ( url, user, password ) ;
    		System.out.println("接続成功");
    	}catch(SQLException e) {
    		System.out.println("接続失敗");
    	}
    }


    //切断
    public static void close() {
    	try {
			con.close();

		} catch (Exception a) {
			System.out.println("切断失敗");
		}
    }


    //テーブルに情報を追加	tableNumについて:player->0,team:1
    public static void addInfo(String[] info,int tableNum) throws SQLException {

	    try {

	    	String[] sql= {"INSERT INTO player VALUES(?,?)",
	    		  		  "INSERT INTO team VALUES(?,?)"};

	    	int[] columnNum= {2,2};
	    	stmt=con.createStatement();
	    	prestmt = con.prepareStatement(sql[tableNum]);
	    	for(int i=0;i<columnNum[tableNum];i++) {
	    		prestmt.setString(i+1,info[i]);
	     	}

	    	int result = prestmt.executeUpdate ();

	    	if(result==1) {
	    		System.out.println("Done");
	    	}else {
	    		System.out.println("unDone");
	    	}
	    } catch ( SQLException e ) {
	      e.printStackTrace() ;
	    }
	}


    //テーブルから情報を削除	tableNumについて:player->0,team:1
    public static void delInfo(int tableNum,String key) throws SQLException {

    	String[] sql= {"DELETE FROM player WHERE 名前=?",
		  				"DELETE FROM team WHERE チーム名=?"};
    	prestmt=con.prepareStatement(sql[tableNum]);
    	prestmt.setString(1,key);
    	int result=prestmt.executeUpdate();
    	if(result==1) {
    		System.out.println("Done");
    	}else {
    		System.out.println("unDone");
    	}
    }

    //行数を数える
    public static int countColmn(int tableNum) throws SQLException {

    	stmt = con.createStatement() ;
  	    String sql = "SELECT * from " +TableTitle[tableNum];
  	    result = stmt.executeQuery ( sql );
  	    int count=0;
  	    while(result.next()) {
  	    	count++;
  	    }
  	    return count;

    }

    //列単位で情報を取得	rowNum->何列目の情報をとるのか,	tableNumについて:player->0,team:1
    public static String[] getRowInfo(int rowNum,int tableNum) throws SQLException {

    	int num=countColmn(tableNum);
    	stmt = con.createStatement() ;
  	    String sql = "SELECT * from " +TableTitle[tableNum];
  	    result = stmt.executeQuery ( sql ) ;
  	    String[] rowInfo=new String[num];
    	int count=0;
    	while(result.next()) {
    		rowInfo[count]=result.getString(rowNum);
    		count++;
    	}
    	return rowInfo;
    }


    //リストを取得 (出力は行)	colmns:項目数	tableNum:player->0,team:1
    public static List<String[]> getList(int colmns,int tableNum) throws SQLException {

    	stmt = con.createStatement() ;
  	    String sql = "SELECT * from " +TableTitle[tableNum];
  	    result = stmt.executeQuery ( sql ) ;
  	    List<String[]> list=new ArrayList<>();

  	    while ( result.next() ) {
  	    	String[] contents=new String[colmns];
  	    	for(int i=0;i<colmns;i++) {
  	    		contents[i] = result.getString (i+1);
  	    	}
  	    	list.add(contents);
  	    }
    	return list;
    }

    //テーブルを作成	colmns:項目数	tableNumについて:player->0,team->1
	public static JScrollPane makeTbl(int colmns,int tableName) throws SQLException {

		String[][] header= {{"所属","名前"},{"チーム名","略称",}};
		List<String[]> tableList=getList(colmns,tableName);
		model=new DefaultTableModel(tableList.toArray(new String[0][0]),header[tableName]);

		table=new JTable(model);
		table.setBounds(300, 10, 300, 400);

		sp=new JScrollPane(table);
		sp.setBounds(300, 10, 300, 400);
		return sp;
	}

}