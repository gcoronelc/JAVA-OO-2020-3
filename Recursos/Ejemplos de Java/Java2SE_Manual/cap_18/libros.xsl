<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
  
  <xsl:template match="/">
    <HTML><HEAD><TITLE>Tutorial de Java, Servlets</TITLE></HEAD>
    <BODY><H2>Servlets, Filtros</H2><BR/>
    <xsl:apply-templates/>
    <table width="70%" cellspacing="0" cellpadding="0" border="0">
    <tr bgcolor="#0000ff"><td align="center" height="16">
      <font face="Arial,Helvetica" size="1"
        color="#FFFFFF">(C) 2002, Agustin Froufe</font></td>
    </tr></table>
    </BODY></HTML>
  </xsl:template>  

  <!-- Presenta los datos del libro -->
  <xsl:template match="libro">
  <xsl:text>Titulo: </xsl:text><B><xsl:value-of select="@titulo"/></B><BR/>
  <xsl:text> Autor: </xsl:text><B><xsl:value-of select="@autor"/></B><BR/>
  <xsl:text>Precio: </xsl:text><B><I><xsl:value-of select="@precio"/></I></B>
  <BR/><BR/>
  </xsl:template>

</xsl:stylesheet>


