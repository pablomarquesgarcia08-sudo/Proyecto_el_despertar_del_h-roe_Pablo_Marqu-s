<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:output method="html" encoding="UTF-8" indent="yes"/>

    <xsl:template match="/">
        <html>
            <head>
                <meta charset="UTF-8"/>
                <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
                <title>Ranking de jugadores</title>
                <style>
                    * {
                    box-sizing: border-box;
                    margin: 0;
                    padding: 0;
                    }

                    body {
                    font-family: Arial, sans-serif;
                    min-height: 100vh;
                    background: linear-gradient(135deg, #0f172a, #1e293b, #111827);
                    color: white;
                    display: flex;
                    justify-content: center;
                    align-items: center;
                    padding: 40px 20px;
                    }

                    .contenedor {
                    width: 100%;
                    max-width: 950px;
                    background: rgba(255, 255, 255, 0.08);
                    border: 1px solid rgba(255, 255, 255, 0.12);
                    border-radius: 24px;
                    box-shadow: 0 20px 50px rgba(0, 0, 0, 0.35);
                    backdrop-filter: blur(12px);
                    -webkit-backdrop-filter: blur(12px);
                    overflow: hidden;
                    }

                    .cabecera {
                    padding: 30px;
                    background: linear-gradient(135deg, #06b6d4, #3b82f6, #8b5cf6);
                    }

                    .cabecera h1 {
                    font-size: 34px;
                    font-weight: bold;
                    margin-bottom: 10px;
                    }

                    .cabecera p {
                    font-size: 16px;
                    opacity: 0.95;
                    }

                    .tabla-wrap {
                    padding: 25px;
                    }

                    table {
                    width: 100%;
                    border-collapse: collapse;
                    overflow: hidden;
                    border-radius: 18px;
                    background: rgba(15, 23, 42, 0.55);
                    }

                    thead th {
                    background: rgba(255, 255, 255, 0.08);
                    color: #e2e8f0;
                    text-transform: uppercase;
                    letter-spacing: 1px;
                    font-size: 14px;
                    padding: 18px 14px;
                    text-align: center;
                    }

                    tbody td {
                    padding: 18px 14px;
                    text-align: center;
                    border-top: 1px solid rgba(255, 255, 255, 0.08);
                    font-size: 16px;
                    }

                    tbody tr {
                    transition: transform 0.2s ease, background 0.2s ease;
                    }

                    tbody tr:hover {
                    background: rgba(255, 255, 255, 0.06);
                    transform: scale(1.01);
                    }

                    .posicion {
                    font-weight: bold;
                    font-size: 18px;
                    }

                    .jugador {
                    font-weight: bold;
                    color: #f8fafc;
                    }

                    .puntos {
                    font-weight: bold;
                    color: #22d3ee;
                    }

                    .top1 {
                    background: linear-gradient(90deg, rgba(255, 215, 0, 0.18), rgba(255, 255, 255, 0.03));
                    }

                    .top2 {
                    background: linear-gradient(90deg, rgba(192, 192, 192, 0.18), rgba(255, 255, 255, 0.03));
                    }

                    .top3 {
                    background: linear-gradient(90deg, rgba(205, 127, 50, 0.20), rgba(255, 255, 255, 0.03));
                    }

                    .badge {
                    display: inline-block;
                    padding: 6px 12px;
                    border-radius: 999px;
                    font-size: 12px;
                    font-weight: bold;
                    letter-spacing: 0.5px;
                    margin-left: 10px;
                    }

                    .oro {
                    background: #facc15;
                    color: #111827;
                    }

                    .plata {
                    background: #cbd5e1;
                    color: #111827;
                    }

                    .bronce {
                    background: #d97706;
                    color: white;
                    }

                    .normal {
                    background: rgba(255, 255, 255, 0.08);
                    color: #e2e8f0;
                    }

                    .footer {
                    padding: 0 25px 25px 25px;
                    color: #cbd5e1;
                    font-size: 14px;
                    text-align: right;
                    }

                    @media (max-width: 700px) {
                    .cabecera h1 {
                    font-size: 26px;
                    }

                    thead th,
                    tbody td {
                    font-size: 13px;
                    padding: 12px 8px;
                    }

                    .badge {
                    display: block;
                    margin: 8px auto 0 auto;
                    width: fit-content;
                    }
                    }
                </style>
            </head>
            <body>
                <div class="contenedor">
                    <div class="cabecera">
                        <h1>🏆 Ranking de jugadores</h1>
                        <p>Clasificación general ordenada por puntuación</p>
                    </div>

                    <div class="tabla-wrap">
                        <table>
                            <thead>
                                <tr>
                                    <th>Posición</th>
                                    <th>Jugador</th>
                                    <th>Puntuación</th>
                                    <th>Estado</th>
                                </tr>
                            </thead>
                            <tbody>
                                <xsl:for-each select="Ranking/jugador">
                                    <xsl:sort select="puntuacion" data-type="number" order="descending"/>

                                    <tr>
                                        <xsl:attribute name="class">
                                            <xsl:choose>
                                                <xsl:when test="position()=1">top1</xsl:when>
                                                <xsl:when test="position()=2">top2</xsl:when>
                                                <xsl:when test="position()=3">top3</xsl:when>
                                                <xsl:otherwise></xsl:otherwise>
                                            </xsl:choose>
                                        </xsl:attribute>

                                        <td class="posicion">
                                            <xsl:value-of select="position()"/>
                                        </td>

                                        <td class="jugador">
                                            <xsl:value-of select="nombre"/>
                                        </td>

                                        <td class="puntos">
                                            <xsl:value-of select="puntuacion"/>
                                        </td>

                                        <td>
                                            <xsl:choose>
                                                <xsl:when test="position()=1">
                                                    <span class="badge oro">🥇 Líder</span>
                                                </xsl:when>
                                                <xsl:when test="position()=2">
                                                    <span class="badge plata">🥈 Top 2</span>
                                                </xsl:when>
                                                <xsl:when test="position()=3">
                                                    <span class="badge bronce">🥉 Top 3</span>
                                                </xsl:when>
                                                <xsl:otherwise>
                                                    <span class="badge normal">Competidor</span>
                                                </xsl:otherwise>
                                            </xsl:choose>
                                        </td>
                                    </tr>
                                </xsl:for-each>
                            </tbody>
                        </table>
                    </div>

                    <div class="footer">
                        Diseño moderno generado a partir de Ranking.xml
                    </div>
                </div>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>