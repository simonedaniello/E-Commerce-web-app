<%--
  Created by IntelliJ IDEA.
  User: dandi
  Date: 23/01/17
  Time: 15.52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ricerca</title>
    <jsp:include page="jspPageTemplates/head.jsp" flush="true" />
</head>

<body>
<jsp:include page="jspPageTemplates/headerArea.jsp" flush="true" />
<jsp:include page="jspPageTemplates/brandingArea.jsp" flush="true" />
<jsp:include page="jspPageTemplates/mainMenuWithoutBar.jsp" flush="true" />

<jsp:useBean id="ArticleBean" scope="session" class="javabean.ArticleBean"/>

<form action="success.jsp" name="myform" method="post">
    <div style="text-align: center">
        <label for="nome">Nome</label> <br> <input name="nome" type="text" id="nome" class="extendedInput"><br>
        <label>
            <input type="radio" name="radiob" value="inf" class="inf" onchange="valueChanged1()">
        </label> Informatica <br>
        <label>
            <input type="radio" name="radiob" value="cloth" class="cloth" onchange="valueChanged2()">
        </label> Abbigliamento <br>
        <label>
            <input type="radio" name="radiob" value="libri" class="libri" onchange="valueChanged3()">
        </label> Libri <br>
        <label>
            <input type="radio" name="radiob" value="scolastico" class="scolastico" onchange="valueChanged4()">
        </label> Libri scolastici <br>
    </div>



    <div id="informatic" style="text-align: center; display: none">
        <div><label for="inftipo">Tipo </label><br><input name="tipo" type="text" id="inftipo" class="extendedInput"></div>
        <div><label for="infvenditore">Venditore </label><br><input name="proprietario" type="text" id="infvenditore" class="extendedInput"></div>
        <div><label for="infnome">Nome </label><br><input name="nome" type="text" id="infnome" class="extendedInput"></div>
        <div><label for="infmodello">Modello </label><br><input name="modello" type="text" id="infmodello" class="extendedInput"></div>
        <div><label for="infmarca">Marca </label><br><input name="marca" type="text" id="infmarca" class="extendedInput"></div>
    </div>
    <div id="abbigli" style="text-align: center; display: none">
        <div><label for="abbtipo">Tipo </label><br><input name="tipo" type="text" id="abbtipo" class="extendedInput"></div>
        <div><label for="abbvenditore">Venditore</label><br><input name="proprietario" type="text" id="abbvenditore" class="extendedInput"></div>
        <div><label for="abbnome">Nome </label><br><input name="nome" type="text" id="abbnome" class="extendedInput"></div>
        <div><label for="abbtaglia">Taglia </label><br><input name="taglia" type="text" id="abbtaglia" class="extendedInput"></div>
        <div><label for="abbmarca">Marca </label><br><input name="marca" type="text" id="abbmarca" class="extendedInput"></div>
    </div>
    <div id="lib" style="text-align: center; display: none">
        <div><label for="libtitolo">Titolo </label><br><input name="titolo" type="text" id="libtitolo" class="extendedInput"></div>
        <div><label for="libvenditore">Venditore </label><br><input name="venditore" type="text" id="libvenditore" class="extendedInput"></div>
        <div><label for="libnome">Nome </label><br><input name="nome" type="text" id="libnome" class="extendedInput"></div>
        <div><label for="libautore">Autore </label><br><input name="autore" type="text" id="libautore" class="extendedInput"></div>
        <div><label for="libcasa">Casa Editrice </label><br><input name="edizione" type="text" id="libcasa" class="extendedInput"></div>
    </div>
    <div id="sco" style="text-align: center; display: none">
        <div><label for="scotitolo">Titolo </label><br><input name="titolo" type="text" id="scotitolo" class="extendedInput"></div>
        <div><label for="scomateria">Materia </label><br><input name="materia" type="text" id="scomateria" class="extendedInput"></div>
        <div><label for="sconome">Nome </label><br><input name="nome" type="text" id="sconome" class="extendedInput"></div>
        <div><label for="scoautore">Autore </label><br><input name="autore" type="text" id="scoautore" class="extendedInput"></div>
        <div><label for="scocasa">Casa Editrice</label> <br><input name="edizione" type="text" id="scocasa" class="extendedInput"></div>
    </div>

    <div style="text-align: center">
        <input name="cerca" type="submit" id="cerca" value="Cerca">
    </div>
</form>



<script>
    function valueChanged1(){
        var i = document.getElementById('informatic');
        i.style.display = "block";
        var c = document.getElementById('abbigli');
        c.style.display = "none";
        var b = document.getElementById('lib');
        b.style.display = "none";
        var d = document.getElementById('sco');
        d.style.display = "none";
    }
    function valueChanged2(){
        var i = document.getElementById('informatic');
        i.style.display = "none";
        var c = document.getElementById('abbigli');
        c.style.display = "block";
        var b = document.getElementById('lib');
        b.style.display = "none";
        var d = document.getElementById('sco');
        d.style.display = "none";
    }
    function valueChanged3(){
        var i = document.getElementById('informatic');
        i.style.display = "none";
        var c = document.getElementById('abbigli');
        c.style.display = "none";
        var b = document.getElementById('lib');
        b.style.display = "block";
        var d = document.getElementById('sco');
        d.style.display = "none";
    }
    function valueChanged4(){
        var i = document.getElementById('informatic');
        i.style.display = "none";
        var c = document.getElementById('abbigli');
        c.style.display = "none";
        var b = document.getElementById('lib');
        b.style.display = "none";
        var d = document.getElementById('sco');
        d.style.display = "block";
    }
</script>


<script type="text/javascript">
    var allRadios = document.getElementsByName('radiob');
    var booRadio;
    var x;
    for(x = 0; x < allRadios.length; x++){

        allRadios[x].onclick = function() {
            if(booRadio == this){
                this.checked = false;
                booRadio = null;
                var i = document.getElementById('informatic');
                i.style.display = "none";
                var c = document.getElementById('abbigli');
                c.style.display = "none";
                var b = document.getElementById('lib');
                b.style.display = "none";
                var d = document.getElementById('sco');
                d.style.display = "none";
            }else{
                booRadio = this;
            }
        };
    }
</script>

</body>
</html>
