<%@include file="/header.jsp" %>
<c:if test="${!empty sessionScope.connected}">
<h1 class="ui header titreSection">Les commandes</h1>


<form action="/Test/CommandeServlet" method="post" class="ui form tiny" style="display:inline-block">
    <div class="field inline">
        <input type="search" name="key"/>  <label><i class="ui search icon"></i></label>       
    </div>
</form>
<br><br>



<button class="ui button primary addCommande"><i class="ui add icon"></i></button>
  <c:if test="${!empty resultPage}">                              
    <a href="/Test/CommandeServlet">Revenir à la liste</a>
    </c:if>

  
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
<div class="ui grid">
    <div class="five column row">
        <div class="column ui header tiny">N° commande</div>
        <div class="column ui header tiny">Client</div>
        <div class="column  ui header tiny">date du commande</div>        
        <div class="column  ui header tiny">Payé</div>

        <div class="column ui header tiny">--</div>

  </div>
     <c:forEach items="${commandes}" var="commande">
        
         <div class="five column row">
            <div class="column">${commande.getIdCommande()}</div>
            <div class="column">${commande.getNomClient()} ${commande.getPrenomClient()}</div>
            <div class="column">${commande.getDateCommande()}</div>
            <div class="column">
                
                  <c:if test="${commande.getPaye() == 0}">                              
            non payé
                    </c:if> 
                    <c:if test="${commande.getPaye() != 0}">                              
                    payé
                    </c:if>
             </div>
            <div class="column">
                <a href="/Test/SingleCommandeServlet?idCommande=${commande.getIdCommande()}" class="ui button voirCommande"><i class="eye icon purple"></i></a>
                 <button class="ui button deleteCommande"><span style="display:none;">${commande.getIdCommande()}</span><i class="ui trash red icon deleteCommande"></i></button>
            </div>
           </div>
          
        </c:forEach>
    
     
   
</div>




<div class="ui modal confirmDeleteCommande tiny">
    <i class="ui close icon red"></i>
    <div class="header">
        <h1>Supprimer la commande ?</h1>
    </div>
    <div class="content" align="center">
        <a  class="lienSuppression ui button red">oui</a> <a href="#" class="ui button purple cancel">non</a>
    </div>
    

         
  <div class="ui modal tiny addCommande">
      <i class="ui close icon red"></i>
      <div class="header" align="center">
          <div class="ui header">Nouveau commande</div>
      </div>
            <div class="content">
                <form action="/Test/CommandeServlet" method="post" class="ui form tiny">

                    <div class="field">
                        <label>idClient</label> <input type="text" name="idClient">
                    </div>
                     <div class="field">
                        <label>Date du commande</label> <input type="date" name="dateCommande">
                    </div>
                    
                    <p align="center">
                    
                    <input type="submit" class="ui button primary submit" value="Enregistrer" />
                    
                    </p>
                    
                </form>
            </div>
        </div>       

 
</c:if>
 <%@include file="/footer.jsp" %>