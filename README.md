> This project has been migrated to Kotlin and the syntax has changed as well.

> The documentation will be updated soon

# Tevim

*TeViM* stands for *Text View Mockup*.  
This is a text based tool for creating mockups in a fast and reusable way.  
You write a simple specific grammar and the tool generates the result. This grammar might be written either as a plain text file or as a Kotlin class, which will be compiled.  

## Overview

* Write a simple file like:

```	
	document {
		states("ab", "ex")
		styles("blue")

		section("main") {
			header("meu Header")
			t("label:")
			itext(); button("salvar")
			t("Radio: "); radio()
			text("Check: "); check("x")
			text("email"); inputemail()
			li("Item")
			li("Item 2")
			cb("Cidades")

			section("subTab") {
				text("inner")
			}
		}
	}

```

This file will be processed and the output will be generated.  
Currently it will create HTML files. In the future other output formats may be added...  

**Advantages**:  

> *But considering HTML is already a text file, why would not I just write it directly?*  

* HTML is a more verbose file (it needs to be more detailed for real runnable systems)
* Ready to use CSS files
	* Tevim plans are about having multiple pre-existing CSS configuration (for while there is only one default)
* There are **additional supported features** like:
	* Templating (create files based on an existing structure)
	* States (generate files for different states of the application)


The idea is inspired mainly by PlantUML.  
This is better explained in [References Page](https://github.com/ssricardo/tevim/wiki/references), along with other technologies from which this is inspired. Take a look!  

## How to use

There are 2 ways of using it.  

**Standalone**:  

The standoalone version uses plain text files to specify the mockup. The tool will interpret the plan and process it.  
This way, you may use it regardless of programming language.  

Execute Tevim as a Java application. Just run a code like:  

> java -jar tevim.jar -i /home/ricardo/myTextFile.txt  


You can also get help:  

> java -jar tevim.jar -help

More details about the [execution and parameters](https://github.com/ssricardo/tevim/wiki/how-use) are available at Wiki.

**Kotlin**:  

Other option is using it as a Kotlin library.  This project uses Kotlin and make use of its Builder syntax. 
Therefore it is possible to add it as a dependency and, using the builders, the syntax will be about the same.  

The advantages of using this way:  

* Make possible to use an IDE, with auto-complete features
* The code will be compiled, making it easier to get (and fix) the errors

## How to write

The grammar is simple. Currently it refers to only a small subset of HTML elements.  
Learn it in [Grammar page](https://github.com/ssricardo/tevim/wiki/grammar) at Wiki.  

### Result

![Order example](./doc/examples/order-details/mockup-orders.png)

For more information, check out the Wiki Pages.  

-------

Some of future steps:  

### TODOs

* Improve documentation
* Ideas:
	* Support images and links? 
	* Include **datasources** to populate the pages with sample data
* Document its architechture

# Contribute

Feel free to open issues and help me to find errors.  
Or, even better, become a project contributor!
