> In progress yet...

# Mockuplan

*Mockuplan* stands for *Mockup Plan Language*.  
This is a text based tool for creating mockups in a fast and simple way.  
Basically you write a text file with a simple specific grammar and the tool generates the result.  


## Overview & Advantages

* Write a simple file like:

```
	---
	---
	
	main
		$ My Page
		label: "  "
		[salvar]
		Radio: ()
		Check: [x]
		email: "@"
		- item
		-item 2
		^Citites^
		subTab
			inner

```

Currently it will generate HTML file (in the future other output formats may be added).

*But if HTML is already a text file, why would not just write it directly?*  

* HTML is a more verbose file (it needs to be more detailed for real runnable system) 
* Ready to use CSSs
	* Mockuplan plans are about having multiple existing CSS configuration (for now there are only one default)
* There are **additional supported features** like:
	* Template (create files based on an existing structure)
	* States (generates files for different states of the application)


The idea is inspired mainly by PlantUML.  
This is better explained in [References Page](https://github.com/ssricardo/mockuplan/wiki/references), along with other technologies from which this is inspired. Take a look!  

## How to use

Executing Mockuplan is really simple. Just run a code like:

> java -jar mockuplan.jar -i /home/ricardo/myTextFile.mpl  


You can also get help:

> java -jar mockuplan.jar -help

More details about the [execution and parameters](https://github.com/ssricardo/mockuplan/wiki/how-use) are available at Wiki.

## How to write

The grammar is simple. Currently it refers to only a small subset of HTML elements.  
Learn it in [Grammar page](https://github.com/ssricardo/mockuplan/wiki/grammar) at Wiki.  


-------

## TODOs

> Still doing a lot of adjustments

* Completar documentações
* Ideias:
	* Incluir imagens, links?
	* Incluir **datasources** para povoar page com exemplos
* Documentar arquitetura
