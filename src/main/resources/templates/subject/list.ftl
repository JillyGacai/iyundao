<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>菜单页</title>
</head>
<body>
    <div>
        <#list userRelation as ur>
            <input type="radio" name="${ur.name}">
        </#list>
    </div>
    <div>
        <ul>
            <li>anymore</li>
            <li>特定用户组</li>
            <li>特定机构</li>
            <li>是否管理员</li>
        </ul>
    </div>
</body>
</html>