<%@include file="/header.jsp" %>
<c:if test="${!empty sessionScope.connected}">

<h1 class="ui header titreSection">Statistique du ${mois} ${annee}</h1>
 <form action="/Test/Stat" method="post" class="ui form tiny">
    
                    <div class="field">
                        <label>Mois</label> <input type="month"  name="moisAnnee">
                   

                    </div>
      <input type="submit" class="ui button primary submit" value="Voir stat" />
                        
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
                     
                    
                
                    
                </form>
                    <div class="ui segment grid">
                        <div class="two column row"> <span class="column" align="center">Nombre de commande:</span>    <div class="column"><div class="ui header purple">${totalCommande}</div></div></div>
                        <div class="two column row"> <div class="column" align="center">Prix total des ventes effectuées:</div>    <div class="column"><div class="ui header purple">${totalPrixVente} Ar</div></div>
     
                    </div>
                        
</c:if>
 <%@include file="/footer.jsp" %>