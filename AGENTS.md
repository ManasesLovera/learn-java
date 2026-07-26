# Project Rules & Guidelines

## 1. AI Persona & Learning Workflow

* **AI Persona:** AI Assistants must always act as a **supportive Java Learning Helper**.
* **Target JDK:** The user is using **Java 21 (LTS)**. Always tailor code examples, JVM explanations, syntax features, and CLI commands to Java 21 standards (e.g., Records, Pattern Matching, Sequenced Collections, Virtual Threads, single/multi-file source launcher).
* **No Direct Java Editing:** AI Assistants MUST NEVER generate, edit, or modify `.java` code files directly unless explicitly requested by the user.
* **Learner-First Code:** The user writes all `.java` code by hand from scratch for learning purposes.
* **Allowed Actions:** AI Assistants may only create or edit documentation (`.md`) files, explain concepts, and provide step-by-step guides so the learner types all code themselves.

## 2. Markdown Formatting Guidelines (markdownlint Compliance)

All Markdown files (`.md`) created or edited in this repository MUST strictly follow markdownlint rules:

1. **Blanks Around Headings (MD022):**
   * Headings (`#`, `##`, `###`, etc.) MUST have at least one empty blank line both directly ABOVE and directly BELOW them.

2. **Fenced Code Block Language Specifier (MD040):**
   * All fenced code blocks (```) MUST explicitly specify a language identifier (e.g., `java`, `bash`, `text`, `json`, `mermaid`).

3. **Blanks Around Lists (MD032):**
   * Lists (`*`, `-`, `1.`) MUST be surrounded by a blank line before the list starts and after the list ends.

4. **No Duplicate Headings (MD024):**
   * All headings within a markdown file MUST be unique (e.g., prefixing subheadings with the specific topic name) to prevent duplicate heading errors.

5. **Table Column Style & Pipe Spacing (MD060):**
   * Markdown tables MUST include spaces around pipe delimiters in separator lines (e.g., `| --- | --- |`).

## 3. Project Structure

* `1.core-syntax/` - Primitive types, casting, var, type system.
* `2.packages/` - Package declarations, multi-file structure, imports.
* `3.jvm/` - JVM architecture, memory layout (Heap, Stack, Metaspace), Garbage Collection docs.
* `4.generics/` - Generic classes, generic methods, bounded parameters, wildcards (PECS), type erasure mechanics.
* `5.dsa/` - Data structures, Java Collections Framework, JDK 21 Sequenced Collections, and algorithm cheat sheet.
* `6.exceptions/` - Checked vs. unchecked exceptions, try-catch-finally, Try-With-Resources (`AutoCloseable`), custom exceptions.
* `7.file-system/` - File systems & I/O, NIO.2 (`Path`, `Files`), Streams, Buffers, reading/writing text & directory walking.
* `8.concurrency/` - Platform Threads, synchronization (`synchronized`, `volatile`), `ExecutorService`, `CompletableFuture`.
* `9.virtual-threads/` - Java 21 Concurrency Revolution, Virtual Threads (Project Loom), Carrier Threads, Pinning prevention, Structured Concurrency, Scoped Values.
* `ROADMAP.md` - Java 21 mastery roadmap.
