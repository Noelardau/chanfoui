<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Facture</title>
        <style>
            
            .companyName{
                font-weight:  bold;
                font-size: 25px;
            }
            
            .header,.footer{
                display: flex;
                justify-content: space-around;
                align-items: center;
            }
            
            .tableauAchat{
                
               display: flex;
               justify-content: center;
               align-items: center;
                
                
            }
            
            table{
                width: 800px;
                
                height: 800px;
                text-align: center;
            }
            
            td,th{
                border: 1px solid black;
            }
            
        </style>
    </head>
    <body>
        <!-- <h1>${commande.getIdCommande()}</h1> -->
        
        <div class="header">
            
            <p>Facture n°:  ${commande.getIdCommande()}</p>
            <div>
                  <span class="companyName">Chanfoui </span>
                   <img src="./assets/image/logo1.jpg" alt="alt" width="50" height="50"/>
            </div>
            
        </div>
            
            <div class="tableauAchat">
                
                <table cellspacing="0">
                    
                    <tr>
                        <th>Designation</th>
                        <th>Quantité acheté</th>
                        <th>Prix unitaire</th>
                        <th>Prix </th>
                    </tr>
                    
                    <c:forEach items="${achats}" var="achat">
                     <tr>
                        <td>${achat.getDesignation()}</td>
                        <td>${achat.getQuantiteAchat()}</td>
                        <td>${achat.getPrixUnitaire()} Ar</td>
                        <td>${achat.getPrixTotal()} Ar</td>
                    </tr>
                    </c:forEach>
                     <tr>
                        <th>Net à payer</th>
                        <th>--</th>
                        <th>--</th>
                        <th> ${netAPayer} Ar</th>
                    </tr>
                    
                </table>
                
                
            </div>
            
            <div class="footer">
            
            <p>Signature du Mr/Mme ${commande.getNomClient()} (client)</p>
            
            <p>Signature du responsable</p>
            
        </div>
            <script>
                
               window.print()
                
            </script>
        
    </body>
</html>
