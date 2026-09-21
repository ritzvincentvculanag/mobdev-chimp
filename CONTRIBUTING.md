# Contributing Guide

Hi everyone! We'll use this guide to standardize our workflow since we're basically doing everything online. I created lang this guide para we are on the same page. Kindly ping me if you are having a hard-time following the instructions para I can demo how to do so.

---

## 1. Before You Start

1. Make sure your local repository is up to date.
2. Always base your work on the `dev` branch.
3. Never work directly on `main` or `dev`.

### Sync your local `dev`

```bash
git checkout dev
git pull origin dev
```

---

## 2. Branch Naming Convention

Create a new branch from `dev` for every assigned task.

### Feature work
```bash
git checkout -b feature/<feature-name>
```

### Bug fixes
```bash
git checkout -b fix/<bug-name>
```

### Chores / maintenance
```bash
git checkout -b chore/<task-name>
```

Use short, clear, lowercase names with hyphens.

Examples:
- `feature/login-screen-ui`
- `fix/crash-on-empty-state`
- `chore/update-readme`

---

## 3. Development Workflow

1. Pull latest updates from `dev`.
2. Create your task branch.
3. Implement your assigned changes.
4. Run and test your changes locally.
5. Commit with a clear message.
6. Push your branch to GitHub.
7. Open a Pull Request targeting `dev`.

---

## 4. Commit Message Format

Use clear and consistent commit messages.

Format:
```text
<type>: <short description>
```

Common types:
- `feat` - new feature
- `fix` - bug fix
- `refactor` - code improvement without behavior change
- `docs` - documentation changes
- `chore` - maintenance work

Examples:
- `feat: add profile editing screen`
- `fix: handle null state in task list`
- `docs: add setup steps for Android Studio`

---

## 5. Push and Create Pull Request

### Push your branch
```bash
git push -u origin <branch-name>
```

### Open a Pull Request
- **Base branch:** `dev`
- **Compare branch:** your branch (`feature/...`, `fix/...`, etc.)

Include in PR description:
1. What was changed
2. Why it was changed
3. Screenshots/video (If makaya lang. If hind, a simple description would suffice <3)
4. Testing done (If makaya lang. If hind, a simple description would suffice <3)
5. Related issue/task link (if available)

---

## 6. Pull Request Checklist

Before requesting review, confirm:
- [ ] Branch is based on latest `dev`
- [ ] App builds successfully
- [ ] No obvious crashes in affected screens
- [ ] Code is readable and follows existing patterns
- [ ] PR targets `dev` (not `main`)
- [ ] PR description is complete

---

## 7. Kotlin + Jetpack Compose Guidelines (Beginner-Friendly)

For consistency and easier reviews:

1. Follow existing code style in the project.
2. Reuse existing components before creating new ones.
3. Keep Composables small and focused.
4. Hard code strings for now.
5. Name things clearly (`TaskCard`, `LoginViewModel`, etc.).
6. If unsure, ask in the PR comments early.

---

## 8. Code Review Expectations

Reviewers may request updates. This is normal.

When changes are requested:
1. Update your branch with fixes.
2. Push new commits to the same branch.
3. Reply to review comments with what you changed.

Do **not** open a new PR for the same task unless instructed.

---

## 9. Common Mistakes to Avoid

- Working directly on `dev` or `main`
- Outdated branch before starting a task
- Very large PRs with unrelated changes
- Unclear commit messages
- PR with no description (ket small description lang oki na)

---

## 10. Quick Command Reference

```bash
# update local dev
git checkout dev
git pull origin dev

# create feature branch
git checkout -b feature/<feature-name>

# stage and commit
git add .
git commit -m "feat: short description"

# push branch
git push -u origin feature/<feature-name>
```

---

## 11. Team Rule of Thumb

If you are blocked for more than 30 minutes:
1. Write what you tried.
2. Share the error/screenshot.
3. Ask for help in the team channel or PR comments.

Early communication saves time for everyone.
