<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:output method="html" encoding="UTF-8" indent="yes"/>

    <xsl:template match="/">

        <html>
            <head>
                <title>Ranking de jugadores</title>
                <link rel="stylesheet" href="Ranking.css"/>
            </head>
            <body>
                <h1>Ranking de jugadores</h1>
                <table border="1" cellpadding="8" cellspacing="0">

                    <tr>
                        <th>Posicion</th>
                        <th>Jugador</th>
                        <th>Puntuacion</th>
                    </tr>

                    <xsl:for-each select="Ranking/jugador">

                        <xsl:sort select="puntuacion"
                                  data-type="number"
                                  order="descending"/>

                        <tr>
                            <td>
                                <xsl:value-of select="position()"/>
                            </td>

                            <td>
                                <xsl:value-of select="nombre"/>
                            </td>

                            <td>
                                <xsl:value-of select="puntuacion"/>
                            </td>
                        </tr>

                    </xsl:for-each>

                </table>

            </body>
        </html>

    </xsl:template>

</xsl:stylesheet>
