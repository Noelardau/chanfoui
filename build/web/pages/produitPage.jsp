<%@include file="/header.jsp" %>
<c:if test="${!empty sessionScope.connected}">

<h1 class="ui header titreSection">Liste des produits:</h1>

<form action="/Test/ProduitServlet" method="post" class="ui form tiny" style="display:inline-block">
    <div class="field inline">
        <input type="search" name="key"/>  <label><i class="ui search icon"></i></label>       
    </div>
</form>
<br><br>



<button class="ui button primary addProduit"><i class="ui add icon"></i></button>
  <c:if test="${!empty resultPage}">                              
    <a href="/Test/ProduitServlet">Revenir à la liste</a>
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
    <div class="four column row">
        <div class="column  ui header tiny">Designation</div>
        <div class="column  ui header tiny">Prix</div>
        <div class="column ui header tiny">Stock</div>
        <div class="column ui header tiny">--</div>

  </div>
      <c:forEach items="${produits}" var="produit">
        
         <div class="four column row">
            <div class="column">${produit.getDesignation()}</div>
            <div class="column">${produit.getPrixProduit()} Ar</div>
            <div class="column">${produit.getQuantiteProduit()}</div>
            <div class="column">
                <a href="/Test/ProduitServlet?idProduitToUpdate=${produit.getIdProduit()}" class="ui button modifyProduit"><i class="ui edit purple icon"></i>Modifier</a>
                <!--   <button class="ui button deleteProd"><span style="display:none;">${produit.getIdProduit()}</span><i class="ui trash red icon deleteProd"></i></button> -->
            </div>
           </div>
          
        </c:forEach>
  
   
</div>


<div class="ui modal confirmDeleteProduit tiny">
     <i class="ui close icon red"></i>
    <div class="header">
        <h1>Supprimer le produit ?</h1>
    </div>
    <div class="content" align="center">
        <a href="#"  class="lienSuppression ui button red">oui</a> <a href="#" class="ui button purple cancel">non</a>
    </div>  
    
</div>
        
  <div class="ui modal tiny addProduit">
       <i class="ui close icon red"></i>
      <div class="header" align="center">
          <div class="ui header"> Nouveau produit </div> 
      </div>
            <div class="content">
                <form action="/Test/ProduitServlet" method="post" class="ui form tiny">

                    <div class="field">
                        <label>Designation</label> <input type="text" name="designation">
                    </div>
                     <div class="field">
                        <label>prix</label> <input type="number" name="prixProduit">
                    </div>
                    <div class="field">
                        <label>Quantité</label> <input type="number" name="quantiteProduit">
                    </div>
                    
                    <p align="center">
                     <input type="submit" class="ui button primary submit" value="Enregistrer" />
                    </p>
                    
                </form>
            </div>
        </div>       
           
       </c:if>   
 <%@include file="/footer.jsp" %>