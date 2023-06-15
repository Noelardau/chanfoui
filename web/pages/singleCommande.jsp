<%@include file="/header.jsp" %>
<c:if test="${!empty sessionScope.connected}">
    <!<!-- modal -->
    

  <div class="ui modal tiny addAchat">
            <div class="header" align="center">Ajouter au panier</div>
            <div class="content">
                <form action="/Test/SingleCommandeServlet" method="post" class="ui form tiny">
                    
                    <input type="number" name="idCommande" value="${commande.getIdCommande()}" hidden="true">                   
                    <input type="number" class="idProduitToAdd" name="idProduit" value="" hidden="true">

                    
                    <div class="field">
                        <label>Quantité</label> <input type="number" name="quantiteAchat">
                    </div>
                    <p align="center">
                        <input type="submit" class="ui button green" value="Ajouter" />                   
                    </p>
                    
                </form>
            </div>
        </div>       
    
    
    <!<!-- modal -->


 <c:if test="${commande.getPaye() == 0}">                           
     <div class="ui grid commande segment" >
    <div class="two column row">
       <div class="column">          
             <p class="ui header" align="center">Produit disponible</p>  
                    
 <c:if test="${!empty error}">          
     <p align="center"> <span class="ui message error"  >${error}</span> </p>               
 </c:if> 
        </div>   
        <div class="column">            
              <p class="ui header" align="center">Panier</p>  
        </div>   
    </div> 
    
    <div class="two column row">
       <div class="column ui segment">          
           <div class="ui grid">    
           <c:forEach items="${produits}" var="produit">
        
         <div class="four column row">
            <div class="column">${produit.getDesignation()}</div>
            <div class="column">${produit.getPrixProduit()} Ar</div>
            <div class="column">${produit.getQuantiteProduit()}</div>
            <div class="column">
                
                 <button class="ui button addToPanier"><span style="display:none;">${produit.getIdProduit()}</span><i class="ui cart plus purple icon deleteProd"></i></button>
            </div>
           </div>
          
        </c:forEach>
        </div>
           
        </div>   
        <div class="column ui segment">            
        Commande numero : ${commande.getIdCommande()}
        
        <div class="ui grid" style="margin-top:20px">
            <div class="five column row">
                <div class="column ui header tiny">Designation</div>
                <div class="column ui header tiny" align="center">Quantité acheté</div>
                <div class="column ui header tiny">prix unitaire</div>
                <div class="column ui header tiny">prix total</div>
                <div class="column">---</div>
            </div>
            
            <c:forEach items="${achats}" var="achat">
                
                 <div class="five column row">
                     <div class="column">${achat.getDesignation()}</div>
                    <div class="column" align="center">${achat.getQuantiteAchat()}</div>
                    <div class="column">${achat.getPrixUnitaire()}</div>
                    <div class="column">${achat.getPrixTotal()}</div>
                    <div class="column"><div class="ui button red"><a style="color:inherit;" href="/Test/SingleCommandeServlet?idCommande=${commande.getIdCommande()}&idAchatToDelete=${achat.getIdAchat()}&idProduit=${achat.getIdProduit()}&quantiteAchete=${achat.getQuantiteAchat()}">Retirer</a></div></div>          
                </div>
                
            </c:forEach>
             <div class="five column row">
                     <div class="column">Net à payer</div>
                    <div class="column" align="center">--</div>
                    <div class="column">--</div>
                    <div class="column">--</div>
                    <div class="column ui header">${netAPayer} Ar</div>          
                </div>
            
        </div>
        
        
        
        
        </div>      
        
    </div> 
                
                <div class="three column row ui segment">
                    <p class="column">
                       
                    </p> 
                    <p class="column" align="center">
                        <c:if test="${netAPayer != 0}"><a href="/Test/SingleCommandeServlet?idCommande=${commande.getIdCommande()}&paye=ok" class="ui button" style="background:#9376E0">Marquer payé</a>
                    </c:if> 
                        </p> 
                    <p class="column">
                        
                    </p>
                </div>           
    
    
    
</div>
 </c:if>
    
    <c:if test="${commande.getPaye() != 0}">  
        <div class="ui grid">
    <div class="row" >
         Commande numero : ${commande.getIdCommande()}
          
    </div> 
    
    <div class="row">
          <div class="column ui segment">            
      
        <div class="ui grid" style="margin-top:20px">
            <div class="four column row">
                <div class="column ui header tiny">Designation</div>
                <div class="column ui header tiny" align="center">Quantité acheté</div>
                <div class="column ui header tiny">prix unitaire</div>
                <div class="column ui header tiny">prix total</div>
                
            </div>
            
            <c:forEach items="${achats}" var="achat">
                
                 <div class="four column row">
                     <div class="column">${achat.getDesignation()}</div>
                    <div class="column" align="center">${achat.getQuantiteAchat()}</div>
                    <div class="column">${achat.getPrixUnitaire()} Ar</div>
                    <div class="column">${achat.getPrixTotal()} Ar</div>
                              
                </div>
                
            </c:forEach>
             <div class="four column row">
                     <div class="column">Net à payer</div>
                    <div class="column" align="center">--</div>
                    <div class="column">--</div>
                    <div class="column ui header">${netAPayer} Ar</div>          
                </div>
            
        </div>
        
        
        
        
        </div>   
        
    </div> 
    
    
    
</div>
                
                    <p align="center" style="margin-top:100px;"><a href="/Test/Facturation?idCommande=${commande.getIdCommande()}" class="ui button" style="background:#9376E0" target="_blank"><i class="ui print icon"></i>Facturation</a></p>
                
    
    </c:if>

</c:if>
 <%@include file="/footer.jsp" %>