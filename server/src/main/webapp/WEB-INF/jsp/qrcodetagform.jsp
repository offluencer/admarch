<html>
<head>
<style>

div {
    height: 600px;
    width: 1000px;
    position: fixed;
    top: 50%;
    left: 50%;
    margin-top: -100px;
    margin-left: -200px;
}
</style>
</head>
<body>

<h1>
<div>
<form method="post" action="qrcode-tag">
    QR code : <input type="text" name="qrcode" style="height: 50px; width: 400px;"/>
    <br> <br>
    Registration number: <input type="text" name="regNo" style="height: 50px; width: 300px;"/>
    <br> <br>
    <label> Type </label>
<select name="type">
<option value = "auto">  AUTO
</option>
<option value = "cab">  CAB
</option>
</select>

<input type="submit" value="Submit" style="height: 50px; width: 300px;">

</form>
</div>
</h1>
</body>
</html>