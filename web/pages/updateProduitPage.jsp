<%@include file="/header.jsp" %>
<c:if test="${!empty sessionScope.connected}">

<h1 class="ui header titreSection">Modifier le produit</h1>

 <c:if test="${!empty error}">          
     <span class="ui message tiny error" >${error}</span>                
 </c:if>

 <form action="/Test/ProduitServlet" method="post" class="ui form tiny">
     <input type="text" value="${produit.getIdProduit()}" hidden="true" name="idProdToUpdate">
                    <div class="field">
                        <label>Designation</label> <input type="text" value="${produit.getDesignation()}" name="designation">
                    </div>
                     <div class="field">
                         <label>prix</label> <input type="number" value="${produit.getPrixProduit()}" name="prixProduit">
                    </div>
                    <div class="field">
                        <label>Quantité</label> <input type="number" value="${produit.getQuantiteProduit()}" name="quantiteProduit">
                    </div>
                    
                    <p align="center">
                    
                    <input type="submit" class="ui button primary submit" value="Enregistrer" />
                    
                    </p>
                    
                </form>
</c:if>
 <%@include file="/footer.jsp" %>