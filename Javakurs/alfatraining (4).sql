-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Erstellungszeit: 16. Nov 2016 um 11:13
-- Server-Version: 10.1.13-MariaDB
-- PHP-Version: 5.6.23

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Datenbank: `alfatraining`
--

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `fachgebiete`
--

CREATE TABLE `fachgebiete` (
  `PRIMARYKEY` int(11) NOT NULL,
  `FACHGEBIET` varchar(100) COLLATE latin1_general_ci NOT NULL,
  `ESPECIALIDAD` varchar(100) COLLATE latin1_general_ci NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

--
-- Daten für Tabelle `fachgebiete`
--

INSERT INTO `fachgebiete` (`PRIMARYKEY`, `FACHGEBIET`, `ESPECIALIDAD`) VALUES
(1, 'Beton', 'Hormigón'),
(2, 'Fassade', 'Fachada'),
(3, 'Fenster und Tueren', 'Ventanas y puertas'),
(4, 'Mauerwerk', 'Albañileria'),
(5, 'Akustik', 'Acústica'),
(6, 'Gebaeudetechnik', 'Instalaciones'),
(7, 'Solar', 'Solar'),
(8, 'Treppen', 'Escaleras'),
(20, 'Baugenehmigung', 'Licencia de obra');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `history`
--

CREATE TABLE `history` (
  `PRIMARYKEY` int(11) NOT NULL,
  `SUCHWORTE` varchar(100) COLLATE latin1_general_ci NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

--
-- Daten für Tabelle `history`
--

INSERT INTO `history` (`PRIMARYKEY`, `SUCHWORTE`) VALUES
(1, ''),
(2, ''),
(3, ''),
(4, '%'),
(5, '%'),
(6, '%');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `lexikon`
--

CREATE TABLE `lexikon` (
  `PRIMARYKEY` int(20) NOT NULL,
  `BEGRIFF` varchar(100) COLLATE latin1_general_ci NOT NULL,
  `TERMINO` varchar(100) COLLATE latin1_general_ci NOT NULL,
  `FACHGEBIET` int(11) NOT NULL,
  `INHALT` text COLLATE latin1_general_ci NOT NULL,
  `CONTENIDO` text COLLATE latin1_general_ci NOT NULL,
  `BEGRIFF_NORMAL` varchar(100) COLLATE latin1_general_ci NOT NULL,
  `TERMINO_NORMAL` varchar(100) COLLATE latin1_general_ci NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

--
-- Daten für Tabelle `lexikon`
--

INSERT INTO `lexikon` (`PRIMARYKEY`, `BEGRIFF`, `TERMINO`, `FACHGEBIET`, `INHALT`, `CONTENIDO`, `BEGRIFF_NORMAL`, `TERMINO_NORMAL`) VALUES
(1, 'Bewehrung', 'Armadura', 1, 'Als Bewehrung bezeichnet man Stahl - oder Fasern, die in den Beton eingelegt sind und Zugkräfte aufnehmen können. Zur Verlegung der Bewehrung wird ein Bewehrungsplan erstellt. Die Bewehrung kann sowohl zur Aufnahme von Zugkräften (meistens aus Biegung - Biegezug) als auch von Druckkräften (Stützen) angeordnet werden. Wird der Stahl mechanisch vorgespannt und unter dieser Spannung eingebaut, spricht man von Spannbeton. Die Verwendung von Glasfaser- oder Kunstfasergeweben ist in der Entwicklung und wird verstärkt eingesetzt.', 'Se denomina armadura al acero- o fibra, que se coloca en el hormigón  para soportar los esfuerzos de tracción. Para la instalación de la armadura se dibujará un plano de armaduras.  La armadura puede colocar tanto para soportar los esfuerzos de tracción (normalmente debidos a la flexion – flexotracción) como para los esfuerzos de compression (apoyos). Si se somete previamente el acero a esfuerzos de compression (pretensado) se denomina hormigón pretensado.  La utilización de fibras de vidrio o fibras sintéticas está en proceso de desarrollo y se va a implanta cada vez con más fuerza.', 'Bewehrung', 'Armadura'),
(2, 'Bindemittel', 'Conglomerante', 1, 'Bindemittel sind im plastischen Zustand verarbeitbare anorganische oder organische Substanzen, die im Laufe einer bestimmten Zeit erhärten und dabei andere Stoffe fest miteinander verbinden. ', 'Los conglomerantes son en estado plástico substancias orgánicas o inorgánicas maleables, que endurecen tras un periodo de tiempo determinado  y con ello unen otras substancias. ', 'Bindemittel', 'Conglomerante'),
(3, 'Rolladen', 'Persiana', 2, 'Rollläden werden als Einbruchschutz und bedingt auch als Schall-, Sonnen-, Blend- bzw. Sichtschutz eingesetzt. Als Hauptfunktion sind Rollläden sicherheitstechnische Eigenschaften zuzuordnen. Im Hinblick auf die wesentlich verbesserten Wärmeschutzverglasungen hat der Rollladen als temporärer Wärmeschutz in der Nacht an Bedeutung verloren.\r\n\r\nAls außen liegender Sonnenschutz ist ein Rollladen sehr wirkungsvoll. Der diffuse Eintrag von Tageslicht jedoch wird bei vielen Produkten fast ausgeschlossen. In der Funktion als Sicht- und Blendschutz wirkt sich ebenfalls der geringe Tageslichteinfall zu Ungunsten der Betriebskosten durch erhöhten Energiebedarf aus. Es werden jedoch inzwischen auch Produkte mit einem guten Tageslichteinfall angeboten.\r\n', 'Las persianas se usan como medida de protección antirrobo y también como barrera antisonido, solar y proteccion visual.', 'Rolladen', 'Persiana'),
(4, 'Floatglas', 'Vidrio flotado', 2, 'Floatglas ist das heute am meisten verwendete Glas. Es besteht aus Siliciumoxid, Calciumoxid, Natriumoxid, Magnesiumoxid und Aluminiumoxid (nach: EN 572 Glas im Bauwesen - Basiserzeugnisse aus Kalk-Natronsilicatglas). Glasdicken von 2 - 25 mm können im Floatglasverfahren hergestellt werden. Floatglas kann durch Aufbringen verschiedener Beschichtungen oder durch Einfärbungen in seinen optischen Qualitäten stark manipuliert werden.', 'El vidrio flotado es el más usado actualmente. Esta compuesto por óxido de Silicio, óxido de calcio, óxido de sodio, óxido de magnesio y óxido aluminio (Alúmina) (según la EN 572). El procedimiento de producción del vidrio flotado permite grosores de 2-25mm. Se puede manipular las cualidades opticas del vidrio flotado a base de diferentes recumbrimientos o tintes.\r\n', 'Floatglas', 'Vidrio flotado'),
(5, 'Belüftung', 'Ventilación', 2, 'Die Belüftung eines Gebäudes kann auf verschiedene Arten gewährleistet werden. Lüftungskonzepte sind früh in die Planung miteinzubeziehen. Umwelteinflüsse, funktionale Anforderungen und ökonomische Zielgrößen sollten bei der Konzeptfindung mit einfließen.\r\nGrundsätzlich werden die natürliche Lüftung und die mechanische Belüftung unterschieden. Die Möglichkeit der natürlichen Belüftung wird maßgeblich beeinflusst von Lärm, Wind, Thermik, Schallübertragung und Umwelteinflüssen wie z.B. Schmutz.\r\n\r\nNatürliche Lüftungskonzepte:\r\n\r\n•	Freie Lüftung: Manuelle Fensterlüftung oder gesteuerte Klappenlüftung\r\n•	Natürlicher Antrieb über Wind oder Thermik: Querlüftung, Druckfassade, Doppelfassade und Atrium\r\nWenn zu jeder Zeit ein bestimmtes Raumklima gefordert wird, große Raumtiefen belüftet werden oder hohe Luftwechselraten und geschlossene Fassaden erforderlich sind, ist das Konzept einer mechanischen Lüftung vorzusehen.\r\n\r\nMechanische Lüftungskonzepte:\r\n\r\n•	Zentrale mechanische Lüftung: Zuluftsystem, Abluftsystem, Zu- und Abluftsystem\r\n•	Dezentrale mechanische Lüftung fassadenintegriert oder raumintegriert\r\n', 'Spa ...', 'Belueftung', 'Ventilacion'),
(6, 'Beschläge', 'Herrajes', 3, 'Beschläge sind alle meist metallenen Teile, die die Öffnungs- und Schließfunktionen von Fenstern und Türen ermöglichen. Beschläge können sowohl mechanisch als auch automatisch bzw. digital gesteuert werde, je nach erforderlicher Funktion.', 'Los herrajes son mayoritariamente piezas metálicas que posibilitan las funciones de aperture y cierre de ventanas y puertas. Los herrajes pueden ser tanto mecánicos como automáticos, es decir, controlados digitalmente, dependiendo de la función necesaria. ', 'Beschlaege', 'Herrajes'),
(7, 'Sprosse', 'Peinazo', 3, 'Der Begriff "Sprosse" bezeichnet eine Leiste, die das Glasfeld des Flügelrahmes unterteilt. Sprossen bestehen üblicherweise aus demselben Material wie der Flügelrahmen, also Holz, Metall oder Kunststoff.', 'El término Peinazo se refiere al listón, que divide el vidrio del batiente de la ventana. Los peinazos son normalmente del mismo material que el marco, es decir madera, metal o plástico.', 'Sprosse', 'Peinazo'),
(8, 'Gewölbe', 'Bóveda', 4, 'Statische Systeme in Form eines Gewölbes können durch ihre Krümmung auch Lasten senkrecht zur Bauteiloberfläche über die sog. Gewölbetragwirkung ableiten. An den Auflagern treten entsprechend der Geometrie des Gewölbes (Krümmungsverlauf, Stich und Stützweite) und der Belastung teilweise hohe Abstützkräfte - der sog. Gewölbeschub - auf. Die Aufnahme dieser Auflagerreaktion mit nur geringen zulässigen Verformungen ist entscheidend für die Tragfähigkeit des Gewölbes. ', 'Los sistemas en forma de bóveda  pueden a través de su curvatura absorber cargas verticales.', 'Gewoelbe', 'Boeveda'),
(9, 'Ringbalken', 'Vigas de contorno', 4, 'Horizontal in der Wandebene liegendes Bauteil zur Aufnahme und Abtragung von Horizontallasten, z.B. aus Wind, Erdbeben und Gebäudeschiefstellung. Der auf Biegung und Zug beanspruchte Ringbalken trägt die Lasten auf die aussteifende Querwände (Schubwände) ab. Als Material wird üblicherweise Stahlbeton verwendet - selten auch Holzbalken oder Stahlprofile.\r\nEin Ringanker ist auf Höhe der Geschossdecken grundsätzlich immer dann erforderlich, wenn die Decken nicht als steife Scheiben ausgebildet sind. \r\n', 'Se colocan verticales a nivel de la pared y absorven cargas horizontales como el viento, movimientos de tierra. Se utiliza normalmente hormigón armado, y menos a menudo madera o perfiles metalicos.', 'Ringbalken', 'Vigas de contorno'),
(10, 'Leichtbeton', 'Hormigón aligerado', 4, 'Nach der Definition der DIN 1045 werden Betonarten mit einem Raumgewicht von 800 bis 2000 kg/m³ als Leichtbeton bezeichnet. Er wird als Zuschlag (Leichtbetonprodukte, Schüttungen, Leichtmauermörtel, etc.), als Mauerstein (Hohlblöcke, Vollblöcke, etc.), als Ergänzungsprodukt (Tragende Stürzen, Rollladenkästen oder Schornsteinbauteile) oder auch als vor Ort gegossenes Element verwendet.\r\n\r\nBei der Herstellung von Leichtbeton besteht die Beimischung aus Gesteinskörnungen mit hoher Porosität. Dabei wird in "gefügedichtem Leichtbeton mit Kornporosität " und "haufwerkporigem Leichtbeton " unterschieden. Außerhalb der DIN Norm werden auch Leichtbetone mit einer untere Gewichtsgrenze bis etwa 350 kg/m³ angeboten. Bei einer Trockenrohdichte von maximal 800 kg/m³ spricht man von Infraleichtbeton oder Ultraleichtbeton. Bei der Herstellung von Infraleichtbeton oder Ultraleichtbeton finden leichte Tongranulate Verwendung. Die Druckfestigkeit dieses Betons ist allerdings derzeit noch so gering, dass er noch nicht als Konstruktionsbeton eingesetzt werden kann. Der besondere Vorteil des Infraleichtbetons liegt vor allem in seiner geringen Wärmeleitfähigkeit. Sein Lambda-Wert ist mit zirka 0,18 W/(mK) ungefähr halb so hoch wie der des Leichtbetons nach DIN 104. ', 'Según la normativa DIN 1045 se denomina hormigón aligerado el hormigón de peso entre 800 y 2000 kg/m³. se usa como suplemento (productos de hormigon aligerado, mortero, etc.), como ladrillo, como producto complementario (dinteles de carga, cajas de persiana o chimeneas) o como element fundido en obra.', 'Leichtbeton', 'Hormigon aligerado'),
(11, 'Abgehängte Unterdecken', 'Falso Techo', 5, 'Zwischen ihrer Oberseite und der Unterseite der Rohdecke gewährleisten abgehängte Unterdecken einen Hohlraum. Dieser Deckenhohlraum bietet den Planern und Betreibern eine Reihe von Vorteilen, insbesondere für die Verlegung der Ver- und Entsorgungsleitungen der einzelnen Räume, für Lüftungs- und Klimaanlagen, Elektroinstallations- und Kommunikationsleitungen. Auch die nachträgliche Wartung dieser Installationen sowie zusätzliche Verlegungen sind möglich, da der Deckenhohlraum bei den meisten Unterdeckensystemen leicht zugänglich bleibt. \r\n\r\nDa jedoch der Deckenhohlraum über mehre Räume eines Stockwerkes hindurchgehen kann, stellt er einen Weg für die Schallübertragung von Raum zu Raum dar (Übertragungsweg a). Dadurch kann die Schalldämmung zwischen benachbarten Räumen stark beeinträchtigt werden. Die Körperschallübertragung (Übertragungsweg b) entlang der Unterdecken bleibt infolge der üblicherweise vorhandenen Konstruktionsfugen meist ohne Bedeutung. Ausnahmen könnten durchgehende Randschienen sein. Es ist deshalb ratsam, stets eine Trennung sämtlicher Befestigungselemente im Wandbereich vorzusehen. Undichtigkeiten (Übertragungsweg c) können in einzelnen Fällen ebenfalls eine Rolle spielen, sind jedoch in der Regel leicht zu beheben.\r\n', 'Spa', 'Abgehaengte Unterdecken', 'Falso Techo'),
(12, 'Schalldämmung', 'Aislamiento acústico', 5, 'Die Schalldämmung ist ein Begriff der Bauakustik, die sich mit der Schallübertragung zwischen angrenzenden Räumen beschäftigt. Die zentrale Frage ist dabei, wie viel Schall von der einen Seite eines Bauteils auf die andere übertragen wird. Die Schalldämmung hängt von der Beschaffenheit der Wände, Decken, Türen oder Fenster ab. Sie sollte möglichst hoch und die Schallübertragung im Gegenzug möglichst gering sein.\r\n\r\nEine hohe Schalldämmung wird in der Regel durch massive schwere Bauteile erreicht, die den Schall an seiner Ausbreitung hindern. Die Schalldämmung eines Bauteils beschreibt das bewertete Schalldämm-Maß R''w, ein Wert, der sich mithilfe einer Bewertungskurve aus dem frequenzabhängigen Bau-Schalldämm-Maß R'' ermitteln lässt. Die Bestimmung von R''w erfolgt durch Messungen vor Ort oder entsprechende Rechenmodelle.', 'El aislamiento acústico en un concepto dentro de la acústica de la edificación que se ocupa de la trasmisión del ruido entre habitaciones contiguas. La pregunta principal es cuanto ruido se va a transmitir de una parte del edificio a la otra. El aislamiento acústico depende de la calidad de las paredes, techos, puertas o ventanas.  Este deberia ser alto y la trasmisión del sonido lo más baja posible.', 'Schalldaemmung', 'Aislamiento acustico'),
(13, 'Biogas', 'Biogas', 6, 'Der erneuerbare Energieträger Biogas entsteht beim bakteriellen Abbau von organischem und tierischem Abfallmaterial unter Licht- und Luftabschluss in einem Faulbehälter. Es besteht zu ca. 60% aus Methan und zu 35% aus Kohlendioxid. Wegen seiner guten Brennbarkeit kann es zum Antrieb von Motoren u.a. für die Stromerzeugung genutzt werden. Beim Verbrennungsprozess wird Methan zu Kohlendioxid und Wasser verbrannt. Da das Methangas hierbei nicht in die Atmosphäre gelangt, wirkt es nicht klimaschädlich. In die Atmosphäre gelangt nur das Abgas (Kohlendioxid), das von den Pflanzen mittels Fotosynthese für deren Zellwachstum eingesetzt wird.', 'El biogas esta compuesto por aprox. 60% metano y 35% dioxid de carbon. Debido a su alta combustabilidad se puede usar para motores. Ya que el gas metano no se tira a la atmosfera, no afecta al medio ambiente. Solo el dioxide de carbon se echa a la atmosfera, con lo que las plantas realizan la fotosintesis.', 'Biogas', 'Biogas'),
(14, 'Geothermie', 'Geotermia', 6, 'Bei der Geothermie wird die im Erdinneren entstehende und gespeicherte Wärmeenergie als Energiequelle genutzt. Zur Gewinnung dieser Wärme stehen verschiedene Systeme zur Verfügung:\r\n\r\n•	Erdkollektoren\r\nDer Erdkollektor ist ein Feld aus Rohrleitungen im Außenbereich. Dabei muss das Feld ungestört von Bäumen sein. Damit ist sowohl eine Beheizung als auch Kühlung möglich, deshalb ist manchmal der Begriff Erdwärmekollektor zu finden. Nach der VDI 4640 Thermische Nutzung des Untergrundes sind dabei je nach Laufzeit und Bodenbeschaffenheit Entzugsleistungen zwischen 8 und 40 W/m² erreichbar. Nachteil ist der relativ große Platzbedarf dieses Systems.\r\n\r\n•	Bodenplattenkühler oder Fundamentspeicher\r\nBei diesem System wird unterhalb der Bodenplatte ein Rohrsystem verlegt, mit dem eine Bauteilkühlung möglich ist, d.h. ein Wärmeeintrag in den Untergrund. Für eine sinnvolle Leistung zu erhalten, ist ein relativ hoher Grundwasserstand nötig. Nachteilig ist, dass aufgrund der Größe der Bodenplatte nur eine bestimmte Entzugsleistung möglich ist.\r\n\r\n•	Energiepfähle\r\nEnergiepfähle sind geothermisch aktivierte Ortbeton- oder Fertigpfähle, Hohlpfähle oder Presspfähle, die zur Energiegewinnung an ein Wärmepumpensystem angeschlossen werden. Wirtschaftlich sinnvoll sind die Pfähle nur, wenn sie aufgrund der erforderlichen Gründung oder der Verbauung sowieso eingebaut werden müssen.\r\n\r\n•	Erdsonden\r\nBei Erd- oder Erdwärmesonden werden Bohrungen im Erdreich hergestellt, die mit Rohrschlangen auf einem Trägermedium bestückt werden. Anschließend werden die Bohrungen mit Betonit vergossen. Die Abstände der Bohrungen müssen mindestens 5 m betragen, die Tiefe der Bohrungen liegt üblicherweise zwischen 50 und 100 m. Erdsonden sind durchaus auch zum Kühlen geeignet.\r\n', 'Spa....', 'Geothermie', 'Geotermia'),
(15, 'Energiebedarf', 'Demanda energética', 7, 'Energiebedarf heißt diejenige Energie, die umgewandelt werden muss, um ein bestimmtes Ziel zu erreichen. Der Heizenergiebedarf ist also die Energie, die benötigt wird, um ein Gebäude zu heizen. Innerhalb der Energiekette unterscheidet man Primärenergie, Sekundärenergie, Endenergie und Nutzenergie.', 'Se denomina demanda energética a la energía que se debe transformar para lograr un cierto objetivo. La demanda de energía calorífica es pues la energía que se necesitará para calentar un edificio. Dentro de la cadena enerética se distinguen la energía primaria, secundaria, energía final y energía útil.', 'Energiebedarf', 'Demanda energetica'),
(16, 'Passivhaus', 'Casa Pasiva', 7, 'Passiv- und Niedrigenergiehäuser haben einen spezifischen Jahresheizenergiebedarf (bezogen auf die beheizte Nutzfläche und die Heizgradtagzahl). \r\nEin Passivhaus ist ein Gebäude, in welchem der Heizwärmebedarf so gering ist, dass ohne Komfortverlust auf ein separates Heizsystem verzichtet werden kann, der Jahresheizwärmebedarf liegt unter 15 kWh/(m²a).', 'Las casas pasivas tienen una demanda anual de calefacción (en relación a la superficie útil calefactada y el grado de calefacción).\r\nUna casa pasiva es un edificio en el que la demanda calorífica es muy baja, lo que permite, sin pérdida de confort, prescindir de un Sistema de calefacción separado. La demanda calorífica anual es menor a 15 kWh/(m²a).', 'Passivhaus', 'Casa Pasiva'),
(17, 'Antritt', 'Escalón de arranque', 8, 'Als Antritt wird die erste Stufe (unten) eines Treppenlaufes nach Verlassen der Geschossebene bezeichnet. Der Antritt kann in folgenden Varianten ausgebildet werden:\r\n\r\n•	die erste Stufe ist aus dem gleichen Material wie die übrigen Stufen\r\n•	die Treppe steht auf einem Sockel auf, der als Antritt fungiert\r\n•	die erste Stufe ist zu einem Podest verbreitert, um mehrere Möglichkeiten der Bewegungsrichtung zu ermöglichen.\r\n', 'Se define como arranque el primer escalon (abajo) de una escalera después de abandoner el nivel del piso. El arranque puede formarse de las distintas formas siguientes:\r\n\r\n•	el primer escalón es del mismo material que los escalones superiors.\r\n•	La escalera está apoyada sobre un pedestal que actúa cómo arranque.\r\n•	El primer escalón se amplia sobre una plataforma, lo que permite varias direcciones de movimiento.\r\n', 'Antritt', 'Escalon de arranque'),
(18, 'Auftritt', 'Huella', 8, 'Jede Treppenstufe setzt sich aus dem Auftritt und der Steigung zusammen. Laut DIN 18065 Gebäudetreppen wird der Auftritt a dabei waagerecht von der Vorderkante einer Stufe bis zur Projektion der Vorderkante der folgenden Stufe in der Lauflinie gemessen. Auftritt und Unterschneidung ergeben zusammen das Maß der Trittstufe . \r\n\r\nDer Auftritt bei Treppen wird immer entlang der sogenannten Lauflinie gemessen. Dies ist vor allem bei Spindel- und Wendeltreppen sowie bei Treppen mit gewendelten Läufen von Bedeutung. Die Lauflinie muss dabei innerhalb eines in der DIN festgelegten Gehbereichs liegen.\r\n\r\nFolgende Maße gelten für Auftritte laut DIN:\r\n\r\n•	Bei Wohngebäuden mit bis zu zwei Wohnungen und innerhalb von Wohnungen muss das Maß a bei baurechtlich notwendigen Treppen mindestens 230 mm, höchstens 370 mm betragen; bei zusätzlichen Treppen (baurechtlich nicht notwendig) genügt ein Mindestauftritt von 210 mm, max. 370 mm.\r\n\r\n•	Bei anderen Gebäuden muss der Mindestauftritt mindestens 260 mm, höchstens 370 mm bei baurechtlich notwendigen Treppen betragen; bei den zusätzlichen Treppen sind Maße von 210 und 370 mm gefordert.  \r\n\r\n•	Für Wendelstufen gelten besondere Anforderungen: Sie müssen bei Wohngebäuden mit bis zu zwei Wohnungen an der schmalsten Stelle der inneren Begrenzung der nutzbaren Laufbreite einen Auftritt von mindestens 50 mm haben (für Spindeltreppen gibt es kein festgelegtes Maß). Bei allen anderen Gebäuden sind 100 mm für den Auftritt gefordert (auch bei Spindeltreppen).\r\nDie Maße gelten für den fertigen Zustand und dürfen nicht durch Fertigungs- und Einbautoleranzen unter- bzw. überschritten werden.\r\n\r\nAuch für Podeste gelten Auftrittsmaße, diese liegen bei dem 2,5-fachen des kleinsten Auftrittes des folgenden Treppenlaufs (bei Wohngebäuden), bzw. bei mindestens dem 3-fachen Auftritt bei allen anderen Gebäuden.\r\n', 'Las escaleras deben poder subirse manteniendo el ritmo para evitar caídas. Para ello, su pendiente ha de ser constante.  Es decir, la razón geométrica entre la profundidad de sus peldaños (llamada huella) y la altura de éstos (llamada contrahuella, tabica o peralte) debe ser constante.', 'Auftritt', 'Huella'),
(19, 'Austritt', 'Huejo', 8, 'Der Austritt ist die letzte oberste Stufe eines Treppenlaufes, der den ebenbündigen Anschluss an die Geschossebene bildet (siehe auch Antritt). Er kann in zwei Varianten ausgebildet werden:\r\n\r\n•	als konstruktiver Teil der Geschossebene, d.h. die Treppenkonstruktion endet eine Stufe vorher oder\r\n\r\n•	als konstruktiver Teil der Treppe, d.h. die Geschossebene schließt ebenbündig daran an.\r\n', 'Spa...', 'Austritt', 'Huejo'),
(31, 'Baumassenzahl', 'Ocupación', 20, 'Die Baumassenzahl ist das Verhältnis des geplanten oder gebauten Volumens von Gebäuden und Gebäudeteilen auf einem Grundstück zur Größe (Fläche) des Grundstücks und gibt an wie viel Kubikmeter Bauvolumen pro Quadratmeter gebaut wird.', 'La ocupación es la relación entre el volumen de las edificaciones de una parcela y el area de la pareclo y se da en m3 por m2 de parcela.', 'Baumassenzahl', 'Ocupacion');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `postleitzahlen`
--

CREATE TABLE `postleitzahlen` (
  `PRIMARYKEY` bigint(20) NOT NULL,
  `PLZ` varchar(10) COLLATE latin1_general_ci NOT NULL,
  `ORT` varchar(100) COLLATE latin1_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

--
-- Indizes der exportierten Tabellen
--

--
-- Indizes für die Tabelle `fachgebiete`
--
ALTER TABLE `fachgebiete`
  ADD PRIMARY KEY (`PRIMARYKEY`);

--
-- Indizes für die Tabelle `history`
--
ALTER TABLE `history`
  ADD PRIMARY KEY (`PRIMARYKEY`);

--
-- Indizes für die Tabelle `lexikon`
--
ALTER TABLE `lexikon`
  ADD PRIMARY KEY (`PRIMARYKEY`);

--
-- AUTO_INCREMENT für exportierte Tabellen
--

--
-- AUTO_INCREMENT für Tabelle `fachgebiete`
--
ALTER TABLE `fachgebiete`
  MODIFY `PRIMARYKEY` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;
--
-- AUTO_INCREMENT für Tabelle `history`
--
ALTER TABLE `history`
  MODIFY `PRIMARYKEY` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT für Tabelle `lexikon`
--
ALTER TABLE `lexikon`
  MODIFY `PRIMARYKEY` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=40;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
