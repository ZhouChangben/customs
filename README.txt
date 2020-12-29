[1]git版本控制与协作开发
git init
git add src
git commit -m  “first commit”
git remote add origin git@github.com:ZhouChangben/customs.git
git push  -u origin master -f

ssh -T git@github.com

[2]mybatis generator自动生成xml文件
mvn -Dmybatis.generator.overwrite=true mybatis-generator:generate

[3]数据库中long类型的时间展示位为可阅读的时间代码
long currentTime = System.currentTimeMillis();
SimpleDateFormat formatter = new SimpleDateFormat("yyyy年-MM月dd日-HH时mm分ss秒");
Date date = new Date(currentTime);
System.out.println(formatter.format(date));
