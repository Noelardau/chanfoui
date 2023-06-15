<%@include file="/header.jsp" %>
 <c:if test="${!empty sessionScope.connected}">
                                   
                       

        
<h1 class="ui header titreSection">Liste des clients:</h1>


<form action="/Test/ClientServlet" method="post" class="ui form tiny" style="display:inline-block">
    <div class="field inline">
        <input type="search" name="key"/>  <label><i class="ui search icon"></i></label>       
    </div>
</form>
<br><br>
<button class="ui button primary addClient"><i class="ui add icon"></i></button>
    <c:if test="${!empty resultPage}">                              
    <a href="/Test/ClientServlet">Revenir à la liste</a>
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
        <div class="column ui header tiny">N° client</div>        
        <div class="column ui header tiny">Nom</div>
        <div class="column ui header tiny">Prénom</div>
        <div class="column ui header tiny">Telephone</div>
        <div class="column ui header tiny">--</div>

  </div>
      <c:forEach items="${clients}" var="client">
        
         <div class="five column row">
            <div class="column">${client.getIdClient()}</div>            
            <div class="column">${client.getNomClient()}</div>
            <div class="column">${client.getPrenomClient()}</div>
            <div class="column">${client.getTelClient()}</div>
            <div class="column">
                <a href="/Test/ClientServlet?idClientToUpdate=${client.getIdClient()}" class="ui button modifyClient"><i class="ui edit purple icon"></i>Modifier</a>
                 <button class="ui button deleteClient"><span style="display:none;">${client.getIdClient()}</span><i class="ui trash red icon deleteClient"></i></button>
            </div>
           </div>
          
        </c:forEach>
  
   
</div>




<div class="ui modal confirmDeleteClient tiny">
     <i class="ui close icon red"></i>
    <div class="header">
        <h1 class="ui header tiny" align="center">Supprimer le client ?</h1>
    </div>
    <div class="content" align="center">
        <a  class="lienSuppression ui button red">oui, supprimer</a> <a href="#" class="ui button purple cancel">non</a>
    </div>
    
    


        
  <div class="ui modal tiny addClient">
       <i class="ui close icon red"></i>
            <div class="header" align="center">
                <div class="ui header" >Nouveau client</div>  
            </div>
            <div class="content">
                <form action="/Test/ClientServlet" method="post" class="ui form tiny">

                    <div class="field">
                        <label>Nom</label> <input type="text" name="nomClient">
                    </div>
                     <div class="field">
                        <label>Prénom</label> <input type="text" name="prenomClient">
                    </div>
                    <div class="field">
                        <label>Tel</label> <input type="tel" name="telClient">
                    </div>
                    <p align="center">
                    
                    <input type="submit" class="ui button primary submit" value="Enregistrer" />
                    
                    </p>
                </form>
            </div>
        </div>       
                   </c:if>

 <%@include file="/footer.jsp" %>
 