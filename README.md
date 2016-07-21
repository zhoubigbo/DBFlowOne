# DBFlow数据库配置及简单操作 #

![](http://i.imgur.com/okVAdv0.gif)

### 操作流程 ###
1. 配置
2. 初始化
3. 建立数据库和表
4. 增删改查
### 第一步：配置 ###
1.1 在项目build.gradle添加apt插件和库

    classpath 'com.neenbedankt.gradle.plugins:android-apt:1.8'

	maven { url "https://www.jitpack.io" }
#### 如图: ####
![](http://i.imgur.com/kfhygUr.png)

1.2 在app的build.gradle中配置

	apply plugin: 'com.neenbedankt.android-apt'

	def dbflow_version = "3.1.1"
	
	apt "com.github.Raizlabs.DBFlow:dbflow-processor:${dbflow_version}"
    compile "com.github.Raizlabs.DBFlow:dbflow-core:${dbflow_version}"
    compile "com.github.Raizlabs.DBFlow:dbflow:${dbflow_version}"
#### 如图: ####
![](http://i.imgur.com/6s5O841.png)
### 第二步：初始化在项目中新建MyApp类，继承Application，并在Manifest中注册，在OnCrate方法中初始化DBFlow ###
    FlowManager.init(new FlowConfig.Builder(getApplicationContext()).build());
#### 如图: ####
![](http://i.imgur.com/stWRTD1.png)
### 第三步：建立数据库和表 ###
3.1 新建AppDatabase类，配置表数据库名称和版本

    @Database(name = AppDatabase.NAME, version = AppDatabase.VERSION)
    public class AppDatabase {
    //数据库名
    public static final String NAME = "DBFlow_DB";
    //数据库版本
    public static final int VERSION = 1;
    }
3.2 新建User表，继承实体基类BaseModel

    @Table(database = AppDatabase.class)
    public class User extends BaseModel {
    
	//设置主键，并自动增长，从1开始
    @PrimaryKey(autoincrement = true)
    private long tableId;
    
    @Column
    private String name;
    
    @Column
    private int age;
    
    public User() {
    super();
    }
    
    public long getTableId() {
    return tableId;
    }
    
    public void setTableId(long tableId) {
    this.tableId = tableId;
    }
    
    public String getName() {
    return name;
    }
    
    public void setName(String name) {
    this.name = name;
    }
    
    public int getAge() {
    return age;
    }
    
    public void setAge(int age) {
    this.age = age;
    }
### 第四步：增删改查 ###

4.1 增加

    User user = new User();
    user.setName("张三");
    user.setAge(18);
    user.save();
4.2 删除

    User user = new User();
    user.setTableId(1);
    user.delete();
4.3 更新

    User user = new User();
    user.setName("李四");
    user.setTableId(1);
    user.delete();
4.4 查询

    List<User> userList = SQLite.select().from(User.class).where(User_Table.name.eq("张三")).queryList();

### 注意：建立数据库和表后以一定要编译一次，不然不能执行表操作 ###

### 官方GitHub [https://github.com/Raizlabs/DBFlow](https://github.com/Raizlabs/DBFlow)###

