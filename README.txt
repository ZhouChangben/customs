[1]git版本控制与协作开发
git init
git add src
git commit -m  “first commit”
git remote add origin git@github.com:ZhouChangben/customs.git
git push  -u origin master

[2]mybatis generator自动生成xml文件
mvn -Dmybatis.generator.overwrite=true mybatis-generator:generate

<li class="dropdown" th:if="${session.user==null}">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Dropdown <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="/login">登录</a></li>
                        <li><a href="/register">注册</a></li>
                        <li><a href="/update">修改</a></li>
                    </ul>
                </li>
                <li class="dropdown" th:if="${session.user!=null}">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Dropdown <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="/" th:text="${session.user.getDcGqname()}"></a></li>
                        <li><a href="/logout">退出</a></li>
                    </ul>
                </li>