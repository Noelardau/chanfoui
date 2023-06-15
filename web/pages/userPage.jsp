<%@include file="/header.jsp" %>
<c:if test="${!empty sessionScope.connected}">
    <h1 class="ui header titreSection">Utilisateur</h1>
 
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

 <c:if test="${!empty success}">          
    <div class="ui modal tiny erreur message green" >
          <div class="header">
            <div class="ui header red">
            
            </div> 
         </div>
         <div class="content">
             ${success}
         </div>
     </div>                     
 </c:if>
 <form action="/Test/User" method="post" class="ui form tiny">
     
                    <div class="field">
                        <label>Pseudo</label> <input type="text" value="${user.getPseudo()}" name="pseudo">
                    </div>
                     <div class="field">
                         <label>Mot de passe</label> <input type="password" value="${user.getPassword()}" name="password"> <br>
                         <a href="#" class="voirPasse">voir mot de passe?</a>
                     </div>
                  
                    
                    <p align="center">
                    
                    <input type="submit" class="ui button primary submit" value="Enregistrer" />
                    
                    </p>
                    
                </form>

    
    
    
    
</c:if>
 <%@include file="/footer.jsp" %>