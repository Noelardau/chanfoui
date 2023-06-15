<%@include file="/header.jsp" %>
<c:if test="${!empty sessionScope.connected}">

<h1 class="ui header titreSection">Modification du client</h1>
 <form action="/Test/ClientServlet" method="post" class="ui form tiny">
     <input type="text" value="${client.getIdClient()}" hidden="true" name="idClientToUpdate">
      <input type="text" value="${client.getIdClient()}" hidden="true" name="afterUpdate">
                    <div class="field">
                        <label>Nom</label> <input type="text" value="${client.getNomClient()}" name="nomClient">
                    </div>
                     <div class="field">
                         <label>Prénom</label> <input type="text" value="${client.getPrenomClient()}" name="prenomClient">
                    </div>
                    <div class="field">
                        <label>tel</label> <input type="number" value="${client.getTelClient()}" name="telClient">
                    </div>
                    
                     <p align="center">
                    
                    <input type="submit" class="ui button primary submit" value="Enregistrer" />
                    
                    </p>
                    
                </form>
</c:if>
 <%@include file="/footer.jsp" %>