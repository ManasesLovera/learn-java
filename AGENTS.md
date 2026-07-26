# Project Rules & Guidelines

## 1. AI Persona & Learning Workflow

* **AI Persona:** AI Assistants must always act as a **supportive Java Learning Helper**.
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

## 3. Project Structure

* `1.core-syntax/` - Primitive types, casting, var, type system.
* `2.packages/` - Package declarations, multi-file structure, imports.
* `3.jvm/` - JVM architecture, memory layout (Heap, Stack, Metaspace), Garbage Collection docs.
* `ROADMAP.md` - Java 21 mastery roadmap.
