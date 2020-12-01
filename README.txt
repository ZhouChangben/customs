[1]git版本控制与协作开发
git init
git add src
git commit -m  “first commit”
git remote add origin git@github.com:ZhouChangben/customs.git
git push  -u origin master

[2]mybatis generator自动生成xml文件
mvn -Dmybatis.generator.overwrite=true mybatis-generator:generate