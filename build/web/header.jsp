<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<!--
Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Html.html to edit this template
-->
<html>
    <head>
        <title>Chanfoui</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="./assets/semantic/semantic.css"/>
    
        <link rel="stylesheet" href="./assets/style.css"/>
        <link rel="shortcut icon" type="image/png" href="./assets/image/logo1.jpg"/>
        <script src="./assets/jquery.js" defer></script>
        <script src="./assets/semantic/semantic.js" defer></script>
        <script src="./assets/app.js" defer></script>

    </head>
    <body>
        .<div class="ui menu fixed">
            <div class="item">
                
                <h1 class="ui header huge titre">Chanfoui</h1> <img src="./assets/image/logo1.jpg" width="100" height="50" alt="alt"/>
              
            </div>   
            
             <c:if test="${!empty sessionScope.connected}">
                                
                 <div class="item" class="active">                
                <a href="/Test/ClientServlet"><i class="ui icon user group red"></i>Client</a>
            </div> 
            <div class="item">                
                <a href="/Test/ProduitServlet"><i class="ui icon list red"></i>Produit</a>
            </div>  
                 <div class="item">                
                <a href="/Test/CommandeServlet"><i class="ui icon shopping cart red"></i>Commande</a>
            </div>  
                 <div class="item">                
                     <a href="/Test/Stat"><i class="ui icon chart bar red"></i> Statistique</a>
            </div> 
                </c:if>
             <c:if test="${!empty sessionScope.connected}">
                <div class="item right">
                 <a href="/Test/User">
                        <i class="user icon purple"></i>
                    </a>
                 </div>
             </c:if>
            
                <c:if test="${!empty sessionScope.connected}">
                    <div class="item">
                          <i class="ui icon log out"></i>

                    </a> 
                      </div>   
                </c:if>
                <c:if test="${empty sessionScope.connected}">
           <div class="item right">
                        <i class="ui icon sign in"></i>
                 </div>

                </c:if>
           

        </div> 
        
     
        <div class="ui container">
        

          
        
<div class="ui modal confirmLogout tiny">
    <i class="ui icon close"></i>
    <div class="header">
        <h1 class="ui header" align="center" style="color:#30A2FF">Se deconnecter?</h1>
    </div>
    <div class="content" align="center">
       <a href="/Test/Home?deconnected=ok" class="lienDeconnexion ui button red">oui, se déconnecter</a>  <a href="#" class="ui button cancel" style="background:#9376E0;">non</a>
    </div>  
    
</div>

                 

           
