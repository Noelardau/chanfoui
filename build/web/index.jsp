<%@include file="/header.jsp" %>
 <c:if test="${!empty error}">          
     <div class="ui modal tiny erreur message red" >
          <div class="header">
            <div class="ui header red">
             Erreur
            </div> 
         </div>
         <div class="content">
             ${error}
         </div>
     </div>                
 </c:if>
<p align="center" style="margin-top: 20%;">
    

    
   
                
          
                 <img src="./assets/image/glass-wine-with-lavender-bush.jpg" alt="image representatif" class="ui image medium center"/>

                 

               
</p>  
        
        <div class="ui modal tiny connexion">
            <i class="ui icon close red"></i>
            <div class="header">
                
                <div class="ui header" align="center">Connexion</div>
            </div>
            <div class="content">
                <form action="/Test/Home" method="post" class="ui form tiny">

                    <div class="field">
                        <label>Pseudo</label> <input type="text" name="pseudo">
                    </div>
                    <div class="field">
                        <label>Mot de passe</label> <input type="password" name="password">
                    </div>
                    <p align="center">
                        <input type="submit" value="se connecter"  class=" ui button connexion primary" />
                    </p>
                    
                </form>
            </div>
        </div>
          
 <%@include file="/footer.jsp" %>

